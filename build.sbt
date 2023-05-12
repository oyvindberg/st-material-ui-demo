import org.scalajs.linker.interface.ModuleSplitStyle

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
    resolvers += MavenRepository(
      "sonatype-snapshots",
      "https://oss.sonatype.org/content/repositories/snapshots"
    ),
    libraryDependencies ++= Seq(
      "com.olvind.st-material-ui" %%% "st-material-ui-icons-scalajs-react" % "5.11.16"
    )
  )
