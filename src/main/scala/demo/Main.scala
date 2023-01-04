package demo

import com.olvind.mui.StyledComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiMaterial.colorsMod.{deepOrange, deepPurple}
import com.olvind.mui.muiMaterial.components as mui
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiStyledEngine.mod.CSSObject
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import com.olvind.mui.react.components.div
import com.olvind.mui.react.mod.CSSProperties
import japgolly.scalajs.react.React.Fragment
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.Implicits.*
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

val Buttons = ScalaFnComponent[Unit] { case () =>
  Fragment(
    mui.Typography.variant("h4")("Basic button"),
    mui.Button.normal.variant("text")("Text"),
    mui.Button.normal.variant("contained")("Contained"),
    mui.Button.normal.variant("outlined")("Outlined"),
    mui.Typography.variant("h4")("Color"),
    mui.Button.normal.color("secondary")("Secondary"),
    mui.Button.normal.variant("contained").color("success")("Success"),
    mui.Button.normal.variant("outlined").color("error")("Error")
  )
}

val Chips = ScalaFnComponent[Unit] { case () =>
  Fragment(
    mui.Chip.label("Chip Filled"),
    mui.Chip.label("Chip Outlines").variant("outlined")
  )
}

val TextFields = ScalaFnComponent[Unit] { case () =>
  Fragment(
    mui.TextField.outlined().id("outlined-basic").label("Outlined"),
    mui.TextField.filled().id("filled-basic").label("Filled"),
    mui.TextField.normal.id("standard-basic").label("Standard")
  )
}

val Avatars = ScalaFnComponent[Unit] { case () =>
  Fragment(
    mui.Avatar("H"),
    mui.Avatar.sx(new SystemCssProperties {
      bgcolor = deepOrange.`500`
    })("N"),
    mui.Avatar.sx(new SystemCssProperties {
      bgcolor = deepPurple.`500`
      this.colorRendering = "optimizeQuality"
    })("OP")
  )
}

val Tooltips = ScalaFnComponent[Unit] { case () =>
  mui.Tooltip
    .title("You don't have permission to do this")
    .followCursor(true)(
      mui.Box.sx(new SystemCssProperties {
        bgcolor = "text.disabled"
        color = "background.paper"
        p = 2
      })("Disabled Action")
    )
}

val Progress = ScalaFnComponent[Unit] { case () =>
  mui.Box.sx(new SystemCssProperties {
    width = "100%"
  })(
    mui.LinearProgress()
  )
}
val Links = ScalaFnComponent[Unit] { case () =>
  Fragment(
    mui.Link.href("#")("Link"),
    mui.Link.href("#").color("inherit")("""{'color="inherit"'}"""),
    mui.Link.href("#").variant("body2")("""{'variant="body2"'}""")
  )
}

val Menu = ScalaFnComponent[Unit] { case () =>
  mui.Paper.sx(new SystemCssProperties {
    width = 320
    maxWidth = "100%"
  })(
    mui.MenuList(
      mui.MenuItem.normal(
        mui.ListItemIcon(muiIcons.ContentCut.fontSize("small")),
        mui.ListItemText()("Cut"),
        mui.Typography.variant("body2").color("text.secondary")("⌘X")
      ),
      mui.MenuItem.normal(
        mui.ListItemIcon(muiIcons.ContentCopy.fontSize("small")),
        mui.ListItemText()("Copy"),
        mui.Typography.variant("body2").color("text.secondary")("⌘C")
      ),
      mui.MenuItem.normal(
        mui.ListItemIcon(muiIcons.ContentPaste.fontSize("small")),
        mui.ListItemText()("Paste"),
        mui.Typography.variant("body2").color("text.secondary")("⌘V")
      ),
      mui.Divider(),
      mui.MenuItem.normal(
        mui.ListItemIcon(muiIcons.Cloud.fontSize("small")),
        mui.ListItemText()("Web Clipboard")
      )
    )
  )
}

val Item = div.styled
  .fn { (theme, props) =>
    new CSSObject {
      padding = theme.spacing(5)
      textAlign = "center"
      color = theme.palette_BaseTheme.text.secondary
    }.combineWith(theme.typography_BaseTheme.body2)
  }
  .build()

val Main = ScalaFnComponent[Unit] { case () =>
  MiniDrawer(
    div(
      mui.Typography.variant("h1")("Material-UI 5 for Scala 3"),
      mui.Container.maxWidth("md")(
        mui.Stack
          .spacing(4.0)(
            Item(mui.Typography.variant("h2")("Autocomplete"), AutoComplete.Asynchronous()),
            Item(mui.Typography.variant("h2")("Avatars"), Avatars()),
            Item(mui.Typography.variant("h2")("Buttons"), Buttons()),
            Item(mui.Typography.variant("h2")("Chips"), Chips()),
            Item(mui.Typography.variant("h2")("Dialog"), Dialog.SimpleDialogDemo()),
            Item(mui.Typography.variant("h2")("Grid"), Grid.FullWidthGrid()),
            Item(mui.Typography.variant("h2")("Links"), Links()),
            Item(mui.Typography.variant("h2")("Menu"), Menu()),
            Item(mui.Typography.variant("h2")("Progress"), Progress()),
            Item(mui.Typography.variant("h2")("Tabs"), Tabs.BasicTabs()),
            Item(mui.Typography.variant("h2")("TextFields"), TextFields()),
            Item(mui.Typography.variant("h2")("Tooltips"), Tooltips())
          )
      )
    )
  )
}

@main
def main: Unit =
  Main().renderIntoDOM(dom.document.getElementById("app"))
