package demo

import com.olvind.mui.StyledComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiMaterial.colorsMod.blue
import com.olvind.mui.muiMaterial.components as mui
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiMaterial.stylesCreateTypographyMod.Variant
import com.olvind.mui.muiStyledEngine.mod.CSSObject
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import com.olvind.mui.react.components.{br, div}
import japgolly.scalajs.react.hooks.Hooks.{UseState, UseStateWithReuse}
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{Callback, ScalaFnComponent}

object Dialog {
  val emails = List("username@gmail.com", "user02@gmail.com")
  case class SimpleDialogProps(
      open: Boolean,
      selectedValue: String,
      onClose: String => Callback
  )
  val SimpleDialog = ScalaFnComponent[SimpleDialogProps] { case SimpleDialogProps(open, selectedValue, onClose) =>
    def handleClose =
      onClose(selectedValue);

    def handleListItemClick(value: String): Callback =
      onClose(value);

    mui
      .Dialog(open = open)
      .onClose((event, reason) => handleClose)(
        mui.DialogTitle("Set backup account"),
        mui.List.sx(new SystemCssProperties[Theme] { pt = 0 })(
          emails.map { email =>
            mui.ListItemButton
              .normal()
              .onClick(_ => handleListItemClick(email))
              .withKey(email)(
                mui.ListItemAvatar(
                  mui.Avatar.sx(new SystemCssProperties[Theme] {
                    bgcolor = blue.`100`;
                    color = blue.`600`
                  })(
                    muiIcons.Person()
                  )
                ),
                mui.ListItemText.primary(email)
              )
          }*
        ),
        mui.ListItemButton.normal
          .autoFocus(true)
          .onClick(value => handleListItemClick("addAccount"))(
            mui.ListItemAvatar(
              mui.Avatar(muiIcons.Add())
            ),
            mui.ListItemText.primary("Add account")
          )
      )
  }

  val SimpleDialogDemo = ScalaFnComponent.withHooks[Unit].useState(false).useState(emails(1)).render {
    (props, open: UseState[Boolean], selectedValue: UseState[String]) =>
      def handleClickOpen = open.modState(open => !open)
      def handleClose(value: String) = open.setState(false) >> selectedValue.setState(value)
      div(
        mui.Typography.variant("subtitle1").component("div")(s"Selected: ${selectedValue.value}"),
        br(),
        mui.Button.DefaultComponentPropsExtendButtonBaseTypAccessKey
          .variant("outlined")
          .onClick(_ => handleClickOpen)(
            "Open simple dialog"
          ),
        SimpleDialog(SimpleDialogProps(selectedValue = selectedValue.value, open = open.value, onClose = handleClose))
      )
  }
}

//import * as React from "react";
//import Button from "@mui/material/Button";
//import Avatar from "@mui/material/Avatar";
//import List from "@mui/material/List";
//import ListItem from "@mui/material/ListItem";
//import ListItemAvatar from "@mui/material/ListItemAvatar";
//import ListItemText from "@mui/material/ListItemText";
//import DialogTitle from "@mui/material/DialogTitle";
//import Dialog from "@mui/material/Dialog";
//import PersonIcon from "@mui/icons-material/Person";
//import AddIcon from "@mui/icons-material/Add";
//import Typography from "@mui/material/Typography";
//import { blue } from "@mui/material/colors";
//
//const emails = ["username@gmail.com", "user02@gmail.com"];
//
//export interface SimpleDialogProps {
//  open: boolean;
//  selectedValue: string;
//  onClose: (value: string) => void;
//}
//
//function SimpleDialog(props: SimpleDialogProps) {
//  const { onClose, selectedValue, open } = props;
//
//  const handleClose = () => {
//    onClose(selectedValue);
//  };
//
//  const handleListItemClick = (value: string) => {
//    onClose(value);
//  };
//
//  return (
//    <Dialog onClose={handleClose} open={open}>
//      <DialogTitle>Set backup account</DialogTitle>
//      <List sx={{ pt: 0 }}>
//        {emails.map((email) => (
//          <ListItem button onClick={() => handleListItemClick(email)} key={email}>
//            <ListItemAvatar>
//              <Avatar sx={{ bgcolor: blue[100], color: blue[600] }}>
//                <PersonIcon />
//              </Avatar>
//            </ListItemAvatar>
//            <ListItemText primary={email} />
//          </ListItem>
//        ))}
//        <ListItem autoFocus button onClick={() => handleListItemClick("addAccount")}>
//          <ListItemAvatar>
//            <Avatar>
//              <AddIcon />
//            </Avatar>
//          </ListItemAvatar>
//          <ListItemText primary="Add account" />
//        </ListItem>
//      </List>
//    </Dialog>
//  );
//}
//
//export default function SimpleDialogDemo() {
//  const [open, setOpen] = React.useState(false);
//  const [selectedValue, setSelectedValue] = React.useState(emails[1]);
//
//  const handleClickOpen = () => {
//    setOpen(true);
//  };
//
//  const handleClose = (value: string) => {
//    setOpen(false);
//    setSelectedValue(value);
//  };
//
//  return (
//    <div>
//      <Typography variant="subtitle1" component="div">
//        Selected: {selectedValue}
//      </Typography>
//      <br />
//      <Button variant="outlined" onClick={handleClickOpen}>
//        Open simple dialog
//      </Button>
//      <SimpleDialog
//        selectedValue={selectedValue}
//        open={open}
//        onClose={handleClose}
//      />
//    </div>
//  );
//}
