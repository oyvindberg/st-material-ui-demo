package demo

import com.olvind.mui.StyledComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiMaterial.anon.PartialInputProps
import com.olvind.mui.muiMaterial.autocompleteAutocompleteMod.AutocompleteRenderInputParams
import com.olvind.mui.muiMaterial.colorsMod.blue
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiMaterial.stylesCreateTypographyMod.Variant
import com.olvind.mui.muiMaterial.{autocompleteAutocompleteMod, components as mui}
import com.olvind.mui.muiStyledEngine.mod.CSSObject
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import com.olvind.mui.react.components.{Fragment, br, div}
import com.olvind.mui.react.mod.{useEffect, useState}
import japgolly.scalajs.react.hooks.Hooks.{UseState, UseStateWithReuse}
import japgolly.scalajs.react.vdom.Implicits.*
import japgolly.scalajs.react.vdom.all.TagMod
import japgolly.scalajs.react.{AsyncCallback, Callback, ScalaFnComponent}
import org.scalajs.dom.window

import scala.scalajs.js

object AutoComplete {
  case class Film(title: String, year: Int)

  def sleep(delay: Int) = new js.Promise[Unit]((resolve, _) => window.setTimeout(() => resolve(()), delay))

  val Asynchronous = ScalaFnComponent.withHooks[Unit].render { case () =>
    val (open, setOpen) = js.Tuple2.toScalaTuple2(useState(false))
    val (options, setOptions) = js.Tuple2.toScalaTuple2(useState(js.Array[Film]()))
    val loading = open && options.length == 0

    useEffect(
      { () =>
        var active = true
        if loading then sleep(1000).`then` { _ => if active then setOptions(topFilms) }
        () => { active = false }
      },
      js.Array(loading)
    )

    useEffect(() => { if (!open) setOptions(js.Array()) }, js.Array(open));

    mui
      .Autocomplete[Film, false, false, false](
        options = options,
        renderInput = (params: AutocompleteRenderInputParams) => {
          val newEndAdornment = Fragment(
            if loading then mui.CircularProgress.color("inherit").size(20) else TagMod.empty,
            params.InputProps.endAdornment
          )

          mui.TextField
            .normal()
            .unsafeSpread(params)
            .label("Asynchronous")
            .InputProps(PartialInputProps().setEndAdornment(newEndAdornment).combineWith(params.InputProps))
            .rawNode
        }
      )
      .set("id", "asynchronous-demo")
      .sx(new SystemCssProperties[Theme] { width = 300 })
      .open(open)
      .onOpen(e => Callback(setOpen(true)))
      .onClose((event, reason) => Callback(setOpen(false)))
      .isOptionEqualToValue((option, value) => option.title == value.title)
      .getOptionLabel {
        case x: String  => x
        case film: Film => film.title
      }
      .loading(loading)

  }

  // Top films as rated by IMDb users. http://www.imdb.com/chart/top
  val topFilms = js.Array(
    Film(title = "The Shawshank Redemption", year = 1994),
    Film(title = "The Godfather", year = 1972),
    Film(title = "The Godfather: Part II", year = 1974),
    Film(title = "The Dark Knight", year = 2008),
    Film(title = "12 Angry Men", year = 1957),
    Film(title = "Schindler's List", year = 1993),
    Film(title = "Pulp Fiction", year = 1994),
    Film(title = "The Lord of the Rings: The Return of the King", year = 2003),
    Film(title = "The Good, the Bad and the Ugly", year = 1966),
    Film(title = "Fight Club", year = 1999),
    Film(title = "The Lord of the Rings: The Fellowship of the Ring", year = 2001),
    Film(title = "Star Wars: Episode V - The Empire Strikes Back", year = 1980),
    Film(title = "Forrest Gump", year = 1994),
    Film(title = "Inception", year = 2010),
    Film(title = "The Lord of the Rings: The Two Towers", year = 2002),
    Film(title = "One Flew Over the Cuckoo's Nest", year = 1975),
    Film(title = "Goodfellas", year = 1990),
    Film(title = "The Matrix", year = 1999),
    Film(title = "Seven Samurai", year = 1954),
    Film(title = "Star Wars: Episode IV - A New Hope", year = 1977),
    Film(title = "City of God", year = 2002),
    Film(title = "Se7en", year = 1995),
    Film(title = "The Silence of the Lambs", year = 1991),
    Film(title = "It's aWonderful Life", year = 1946),
    Film(title = "Life Is Beautiful", year = 1997),
    Film(title = "The Usual Suspects", year = 1995),
    Film(title = "Léon: The Professional", year = 1994),
    Film(title = "Spirited Away", year = 2001),
    Film(title = "Saving Private Ryan", year = 1998),
    Film(title = "Once Upon a Time in the West", year = 1968),
    Film(title = "American History X", year = 1998),
    Film(title = "Interstellar", year = 2014)
  )

}

//export default function Asynchronous() {
//  const [open, setOpen] = React.useState(false);
//  const [options, setOptions] = React.useState<readonly Film[]>([]);
//  const loading = open && options.length === 0;
//
//  React.useEffect(() => {
//    let active = true;
//
//    if (!loading) {
//      return undefined;
//    }
//
//    (async () => {
//      await sleep(1e3); // For demo purposes.
//
//      if (active) {
//        setOptions([...topFilms]);
//      }
//    })();
//
//    return () => {
//      active = false;
//    };
//  }, [loading]);
//
//  React.useEffect(() => {
//    if (!open) {
//      setOptions([]);
//    }
//  }, [open]);
//
//  return (
//    <Autocomplete
//      id="asynchronous-demo"
//      sx={{ width: 300 }}
//      open={open}
//      onOpen={() => {
//        setOpen(true);
//      }}
//      onClose={() => {
//        setOpen(false);
//      }}
//      isOptionEqualToValue={(option, value) => option.title === value.title}
//      getOptionLabel={(option) => option.title}
//      options={options}
//      loading={loading}
//      renderInput={(params) => (
//        <TextField
//          {...params}
//          label="Asynchronous"
//          InputProps={{
//            ...params.InputProps,
//            endAdornment: (
//              <React.Fragment>
//                {loading ? <CircularProgress color="inherit" size={20} /> : null}
//                {params.InputProps.endAdornment}
//              </React.Fragment>
//            ),
//          }}
//        />
//      )}
//    />
//  );
//}
//
//// Top films as rated by IMDb users. http://www.imdb.com/chart/top
//const topFilms = [
//  { title: "The Shawshank Redemption", year: 1994 },
//  { title: "The Godfather", year: 1972 },
//  { title: "The Godfather: Part II", year: 1974 },
//  { title: "The Dark Knight", year: 2008 },
//  { title: "12 Angry Men", year: 1957 },
//  { title: "Schindler"s List", year: 1993 },
//  { title: "Pulp Fiction", year: 1994 },
//  {
//    title: "The Lord of the Rings: The Return of the King",
//    year: 2003,
//  },
//  { title: "The Good, the Bad and the Ugly", year: 1966 },
//  { title: "Fight Club", year: 1999 },
//  {
//    title: "The Lord of the Rings: The Fellowship of the Ring",
//    year: 2001,
//  },
//  {
//    title: "Star Wars: Episode V - The Empire Strikes Back",
//    year: 1980,
//  },
//  { title: "Forrest Gump", year: 1994 },
//  { title: "Inception", year: 2010 },
//  {
//    title: "The Lord of the Rings: The Two Towers",
//    year: 2002,
//  },
//  { title: "One Flew Over the Cuckoo"s Nest", year: 1975 },
//  { title: "Goodfellas", year: 1990 },
//  { title: "The Matrix", year: 1999 },
//  { title: "Seven Samurai", year: 1954 },
//  {
//    title: "Star Wars: Episode IV - A New Hope",
//    year: 1977,
//  },
//  { title: "City of God", year: 2002 },
//  { title: "Se7en", year: 1995 },
//  { title: "The Silence of the Lambs", year: 1991 },
//  { title: "It"s a Wonderful Life", year: 1946 },
//  { title: "Life Is Beautiful", year: 1997 },
//  { title: "The Usual Suspects", year: 1995 },
//  { title: "Léon: The Professional", year: 1994 },
//  { title: "Spirited Away", year: 2001 },
//  { title: "Saving Private Ryan", year: 1998 },
//  { title: "Once Upon a Time in the West", year: 1968 },
//  { title: "American History X", year: 1998 },
//  { title: "Interstellar", year: 2014 },
//];
