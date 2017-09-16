val projectName = "loan-dojo"

resolvers += Resolver.sonatypeRepo("releases")

lazy val commonSettings = Seq(
  organization := "edu.jproyo.dojos",
  name := projectName,
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.2"
)

lazy val commonDeps = Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "com.github.kxbmap" %% "configs" % "0.4.4"
)

lazy val testing = Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "it,test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % "it,test"
)

lazy val dependencies = Seq(
	libraryDependencies ++= commonDeps ++ testing
)

scalacOptions += "-feature"
coverageEnabled := true

lazy val mainClassName = "edu.jproyo.dojos.loan.App"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(commonSettings: _*)
  .settings(dependencies: _*)
  .settings(Defaults.itSettings: _*)
  .settings(
  	mainClass in Compile := Some(mainClassName)
  )

  packAutoSettings ++ Seq(
    packMain := Map(projectName -> mainClassName),
    packJvmOpts := Map(projectName -> Seq("-Xmx2048m")),
    packGenerateWindowsBatFile := false,
    packJarNameConvention := "default",
    packExpandedClasspath := false
  )
