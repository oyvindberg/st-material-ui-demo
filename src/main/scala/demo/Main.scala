package demo

import com.olvind.mui.muiMaterial.colorsMod.{deepOrange, deepPurple}
import com.olvind.mui.muiMaterial.components as mui
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

val Main = ScalaFnComponent[Unit] { case () =>
  <.div(
    mui.Chip.label("Chip Filled"),
    mui.Chip.label("Chip Filled").variant("outlined"),
    mui.Avatar("H"),
    mui.Avatar.sx(new SystemCssProperties {
      bgcolor = deepOrange.`500`
    })("N"),
    mui.Avatar.sx(new SystemCssProperties {
      bgcolor = deepPurple.`500`
    })("OP"),
    mui.Tooltip
      .title("You don't have permission to do this")
      .followCursor(true)(
        mui.Box.sx(new SystemCssProperties {
          bgcolor = "text.disabled"
          color = "background.paper"
          p = 2
        })("Disabled Action")
      ),
    mui.Box.sx(SystemCssProperties().setWidth("100%"))(
      mui.LinearProgress()
    ),
    mui.Link.href("#")("Link"),
    mui.Link.href("#").color("inherit")("""{'color="inherit"'}"""),
    mui.Link.href("#").variant("body2")("""{'variant="body2"'}"""),
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
  )
}

@main
def main: Unit =
  Main().renderIntoDOM(dom.document.getElementById("app"))
