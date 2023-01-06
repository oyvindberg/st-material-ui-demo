# Material-ui 5 for Scala demo

This demonstrates using 
[Scala.js](https://www.scala-js.org/) 
with [React](https://reactjs.org/) 
and [Material-ui](https://mui.com).

This is accomplished by using these Scala.js wrappers: 
- [scalajs-react](https://github.com/japgolly/scalajs-react) for React
- [st-material-ui](https://github.com/oyvindberg/st-material-ui) for Material-ui 

Are you using [slinky](https://slinky.dev)? Check out the [slinky demos](https://github.com/wiringbits/st-material-ui-demo-slinky).

## I just want to see it 
You can see this deployed at [https://oyvindberg.github.io/st-material-ui-demo/](https://oyvindberg.github.io/st-material-ui-demo/)

The source code is [here](https://github.com/oyvindberg/st-material-ui-demo/blob/main/src/main/scala/demo/Main.scala)

## Install

You need to explicitly install the following software:

* sbt, as part of [getting started with Scala](https://docs.scala-lang.org/getting-started/index.html) (or if you prefer, through [its standalone download](https://www.scala-sbt.org/download.html))
* [Node.js](https://nodejs.org/en/)

Other software will be downloaded automatically by the commands below.

## Prepare

Before doing anything, including before importing in an IDE, run

```
$ npm install
```

## Development

Open two terminals.
In the first one, start `sbt` and, within, continuously build the Scala.js project:

```
$ sbt
...
> ~fastLinkJS
...
```

In the second one, start the Vite development server with

```
$ npm run dev
...
```

Follow the URL presented to you by Vite to open the application.

You can now continuously edit the `Main.scala` file, and Vite will automatically reload the page on save.

## Production build

Make a production build with

```
$ npm run build
```

You can then find the built files in the `dist/` directory.
You will need an HTTP server, such as `python3 -m http.server`, to open the files, as Vite rewrites `<script>` tags to prevent cross-origin requests.
