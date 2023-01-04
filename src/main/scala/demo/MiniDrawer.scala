package demo

import com.olvind.mui.StyledComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcon
import com.olvind.mui.muiMaterial.anon.Partial
import com.olvind.mui.muiMaterial.components as mui
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiStyledEngine.mod.CSSObject
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import com.olvind.mui.react.components.div
import com.olvind.mui.react.mod.CSSProperties
import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.Hooks.UseState
import japgolly.scalajs.react.vdom.Implicits.*

import scala.annotation.unused
import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSName

type Css = SystemCssProperties[Theme]

private val drawerWidth = 240

private def openedMixin(theme: Theme) = new CSSObject {
  width = drawerWidth
  transition = theme.transitions_BaseTheme.create(
    "width",
    new Partial {
      easing = theme.transitions_BaseTheme.easing.sharp
      duration = theme.transitions_BaseTheme.duration.enteringScreen
    }
  )
  overflowX = "hidden"
}

private def closedMixin(theme: Theme) =
  new CSSObject {
    transition = theme.transitions_BaseTheme.create(
      "width",
      new Partial {
        easing = theme.transitions_BaseTheme.easing.sharp
        duration = theme.transitions_BaseTheme.duration.leavingScreen
      }
    )
    overflowX = "hidden"
    width = s"calc(${theme.spacing(7)} + 1px)"

    // todo: figure out how to apply this
    // theme.breakpoints.up("sm")
    //  [theme.breakpoints.up('sm')]: {
    //  width: `calc(${theme.spacing(8)} + 1px)`,
    // },
  }

val DrawerHeader = ScalaFnComponent.justChildren { c =>
  div.styled
    .fn { (theme, _) =>
      new CSSObject {
        display = "flex"
        alignItems = "center"
        justifyContent = "flex-end"
        padding = theme.spacing(0, 1)
      }
        // necessary for content to be below app bar
        .combineWith(theme.mixins_BaseTheme.toolbar)
    }
    .build()(c)
}

val AppBar = ScalaFnComponent.withChildren[Boolean] { (open, c) =>
  mui.AppBar.styled
    .fn((theme, _) =>
      new CSSObject {
        zIndex = theme.zIndex_BaseTheme.drawer + 1
        transition = theme.transitions_BaseTheme.create(
          js.Array("width", "margin"),
          new Partial {
            easing = theme.transitions_BaseTheme.easing.sharp
            duration = theme.transitions_BaseTheme.duration.leavingScreen
          }
        )

        if (open) {
          marginLeft = drawerWidth
          width = s"calc(100% - ${drawerWidth}px)"
          transition = theme.transitions_BaseTheme.create(
            js.Array("width", "margin"),
            new Partial {
              easing = theme.transitions_BaseTheme.easing.sharp
              duration = theme.transitions_BaseTheme.duration.enteringScreen
            }
          )
        }
      }
    )
    .build()
    .position("fixed")(c)
}

// todo: hack to set the css property
trait CssDrawerPage extends CSSObject {
  @unused
  @JSName("& .MuiDrawer-paper")
  var `MuiDrawer-paper`: js.UndefOr[Any | CSSObject] = js.undefined
}

val Drawer = ScalaFnComponent.withChildren[Boolean] { (open, c) =>
  mui.Drawer.styled
    .fn((theme, _) =>
      new CssDrawerPage {
        width = drawerWidth
        flexShrink = 0
        whiteSpace = "nowrap"
        boxSizing = "border-box"
        `MuiDrawer-paper` =
          if (open) openedMixin(theme)
          else closedMixin(theme)
      }.combineWith(
        if (open) openedMixin(theme)
        else closedMixin(theme)
      )
    )
    .build()
    .variant("permanent")(c)
}

/** Port of https://mui.com/material-ui/react-drawer/#mini-variant-drawer */
val MiniDrawer = ScalaFnComponent
  .withHooks[Unit]
  .withPropsChildren
  .useState(true)
  .render { (_, children, openS: UseState[Boolean]) =>
    val open: Boolean = openS.value
    def handleDrawerOpen(@unused any: Any): Callback = openS.setState(true)
    def handleDrawerClose(@unused any: Any): Callback = openS.setState(false)

    mui.Box.sx(new Css { display = "flex" })(
      mui.CssBaseline(),
      AppBar(open)(
        mui.Toolbar(
          mui.IconButton.normal
            .color("inherit")
            .`aria-label`("open drawer")
            .onClick(handleDrawerOpen)
            .edge("start")
            .sx(new Css {
              marginRight = 5
              if (open) display = "none"
            })(muiIcon.Menu()),
          mui.Typography.variant("h6").noWrap(true).component("div")("Mini variant drawer")
        )
      ),
      Drawer(open)(
        DrawerHeader(
          mui.IconButton.normal
            .onClick(handleDrawerClose)(
              muiIcon.ChevronLeft() // todo: how to get hold of the theme? {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
            )
        ),
        mui.Divider(),
        mui.List()(
          List("Inbox", "Starred", "Send mail", "Drafts").zipWithIndex.toTagMod { case (text, index) =>
            mui.ListItem
              .normal()
              .withKey(s"$text")
              .disablePadding(true)
              .sx(new Css { display = "block" })(
                mui.ListItemButton
                  .normal()
                  .sx(new Css {
                    minHeight = 48
                    justifyContent = if (open) "initial" else "center"
                    px = 2.5
                  })(
                    mui
                      .ListItemIcon()
                      .sx(new Css {
                        minHeight = 0
                        mr = if (open) 3 else "auto"
                        justifyContent = "center"
                      })(if (index % 2 == 0) muiIcon.Inbox() else muiIcon.Mail()),
                    mui.ListItemText.primary(text).sx(new Css { opacity = if (open) 1 else 0 })
                  )
              )
          }
        ),
        mui.Divider(),
        mui.List()(
          List("All mail", "Trash", "Spam").zipWithIndex.toTagMod { case (text, index) =>
            mui.ListItem
              .normal()
              .withKey(s"$text")
              .disablePadding(true)
              .sx(new Css {
                display = "block"
              })(
                mui.ListItemButton
                  .normal()
                  .sx(new Css {
                    minHeight = 48
                    justifyContent = if (open) "initial" else "center"
                    px = 2.5
                  })(
                    mui
                      .ListItemIcon()
                      .sx(new Css {
                        minHeight = 0
                        mr = if (open) 3 else "auto"
                        justifyContent = "center"
                      })(if (index % 2 == 0) muiIcon.Inbox() else muiIcon.Mail()),
                    mui.ListItemText
                      .primary(text)
                      .sx(new Css {
                        opacity = if (open) 1 else 0
                      })
                  )
              )
          }
        )
      ),
      mui.Box
        .sx(new Css { flexGrow = 1; p = 3 })
        .component("main")(
          DrawerHeader(),
          mui.Typography.paragraph(true)(
            """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
              |tempor incididunt ut labore et dolore magna aliqua. Rhoncus dolor purus non
              |enim praesent elementum facilisis leo vel. Risus at ultrices mi tempus
              |imperdiet. Semper risus in hendrerit gravida rutrum quisque non tellus.
              |Convallis convallis tellus id interdum velit laoreet id donec ultrices.
              |Odio morbi quis commodo odio aenean sed adipiscing. Amet nisl suscipit
              |adipiscing bibendum est ultricies integer quis. Cursus euismod quis viverra
              |nibh cras. Metus vulputate eu scelerisque felis imperdiet proin fermentum
              |leo. Mauris commodo quis imperdiet massa tincidunt. Cras tincidunt lobortis
              |feugiat vivamus at augue. At augue eget arcu dictum varius duis at
              |consectetur lorem. Velit sed ullamcorper morbi tincidunt. Lorem donec massa
              |sapien faucibus et molestie ac.
              |""".stripMargin
          ),
          children
        )
    )
  }
