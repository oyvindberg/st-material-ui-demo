package demo

import com.olvind.mui.StyledComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiMaterial.colorsMod.{deepOrange, deepPurple}
import com.olvind.mui.muiMaterial.components as mui
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiStyledEngine.mod.CSSObject
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import com.olvind.mui.react.components.div
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

val Chips = ScalaFnComponent[Unit] { case () =>
  <.div(
    mui.Chip.label("Chip Filled"),
    mui.Chip.label("Chip Outlines").variant("outlined")
  )
}

val Avatars = ScalaFnComponent[Unit] { case () =>
  <.div(
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
  <.div(
    mui.Tooltip
      .title("You don't have permission to do this")
      .followCursor(true)(
        mui.Box.sx(new SystemCssProperties {
          bgcolor = "text.disabled"
          color = "background.paper"
          p = 2
        })("Disabled Action")
      )
  )
}
val Progress = ScalaFnComponent[Unit] { case () =>
  <.div(
    mui.Box.sx(new SystemCssProperties {
      width = "100%"
    })(
      mui.LinearProgress()
    )
  )
}
val Links = ScalaFnComponent[Unit] { case () =>
  <.div(
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

val Main = ScalaFnComponent[Unit] { case () =>
  <.div(
    Chips(),
    Avatars(),
    Tooltips(),
    Progress(),
    Links(),
    Menu(),
    Grid.FullWidthGrid(),
    Tabs.BasicTabs(),
  )
}

@main
def main: Unit =
  Main().renderIntoDOM(dom.document.getElementById("app"))
