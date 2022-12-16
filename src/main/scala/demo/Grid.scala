package demo

import com.olvind.mui.StyledComponent
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiStyledEngine.mod.CSSObject
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import japgolly.scalajs.react.ScalaFnComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiMaterial.colorsMod.{deepOrange, deepPurple}
import com.olvind.mui.muiMaterial.components as mui
import japgolly.scalajs.react.vdom.html_<^.*

object Grid {

  val Item: StyledComponent[mui.Paper.Builder] = {
    mui.Paper.styled
      .fn((theme, in) =>
        new CSSObject {
          backgroundColor = if (theme.palette.mode == "dark") "#1A2027" else "#fff"
          padding = theme.spacing(1)
          textAlign = "center"
          color = theme.palette_BaseTheme.text.secondary
        }.combineWith(theme.typography_BaseTheme.body2)
      )
      .build()
  }

  val FullWidthGrid = ScalaFnComponent[Unit] { case () =>
    mui.Box.sx(new SystemCssProperties[Theme] {
      flexGrow = 1.0
    })
    mui.Grid2
      .container(true)
      .spacing(2.0)(
        mui.Grid2.xs(6).md(8)(Item("xs=6 md=8")),
        mui.Grid2.xs(6).md(4)(Item("xs=6 md=4")),
        mui.Grid2.xs(6).md(4)(Item("xs=6 md=4")),
        mui.Grid2.xs(6).md(8)(Item("xs=6 md=8"))
      )
  }
}


// import * as React from 'react';
//import { styled } from '@mui/material/styles';
//import Box from '@mui/material/Box';
//import Paper from '@mui/material/Paper';
//import Grid from '@mui/material/Unstable_Grid2';
//
//const Item = styled(Paper)(({ theme }) => ({
//  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
//  ...theme.typography.body2,
//  padding: theme.spacing(1),
//  textAlign: 'center',
//  color: theme.palette.text.secondary,
//}));
//
//export default function FullWidthGrid() {
//  return (
//    <Box sx={{ flexGrow: 1 }}>
//      <Grid container spacing={2}>
//        <Grid xs={6} md={8}>
//          <Item>xs=6 md=8</Item>
//        </Grid>
//        <Grid xs={6} md={4}>
//          <Item>xs=6 md=4</Item>
//        </Grid>
//        <Grid xs={6} md={4}>
//          <Item>xs=6 md=4</Item>
//        </Grid>
//        <Grid xs={6} md={8}>
//          <Item>xs=6 md=8</Item>
//        </Grid>
//      </Grid>
//    </Box>
//  );
//}