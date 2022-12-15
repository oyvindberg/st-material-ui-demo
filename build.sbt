import org.scalajs.linker.interface.ModuleSplitStyle

val publicDev = taskKey[String]("output directory for `npm run dev`")
val publicProd = taskKey[String]("output directory for `npm run build`")

lazy val `st-material-ui-demo` = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := "3.2.1",
    scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(
          ModuleSplitStyle.SmallModulesFor(List("st-material-ui-demo"))
        )
    },
    libraryDependencies ++= Seq(
      "com.olvind.st-material-ui" %%% "st-material-ui-icons-scalajs-react" % "0.1"
    ),
    publicDev := linkerOutputDirectory((Compile / fastLinkJS).value)
      .getAbsolutePath(),
    publicProd := linkerOutputDirectory((Compile / fullLinkJS).value)
      .getAbsolutePath()
  )

def linkerOutputDirectory(
    v: Attributed[org.scalajs.linker.interface.Report]
): File = {
  v.get(scalaJSLinkerOutputDirectory.key).getOrElse {
    throw new MessageOnlyException(
      "Linking report was not attributed with output directory. " +
        "Please report this as a Scala.js bug."
    )
  }
}
