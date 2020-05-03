name := "binance-scala-client"

lazy val scala212               = "2.12.10"
lazy val scala213               = "2.13.1"
lazy val supportedScalaVersions = List(scala212, scala213)

ThisBuild / scalafmtOnCompile := false
ThisBuild / organization := "io.github.paoloboni"

lazy val root = (project in file("."))
  .settings(
    scalaVersion := scala213,
    releaseCrossBuild := true,
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      "io.circe"               %% "circe-core"          % "0.13.0",
      "io.circe"               %% "circe-generic"       % "0.13.0",
      "co.fs2"                 %% "fs2-core"            % "2.3.0",
      "org.typelevel"          %% "cats-core"           % "2.2.0-M1",
      "org.typelevel"          %% "cats-effect"         % "2.1.3",
      "org.systemfw"           %% "upperbound"          % "0.3.0",
      "io.laserdisc"           %% "log-effect-core"     % "0.12.2",
      "io.laserdisc"           %% "log-effect-fs2"      % "0.12.2",
      "org.slf4j"              % "slf4j-api"            % "1.7.28",
      "org.http4s"             %% "http4s-blaze-client" % "0.21.4",
      "org.http4s"             %% "http4s-circe"        % "0.21.4",
      "io.lemonlabs"           %% "scala-uri"           % "2.2.0",
      "com.beachape"           %% "enumeratum"          % "1.6.0",
      "com.beachape"           %% "enumeratum-circe"    % "1.6.0",
      "com.chuusai"            %% "shapeless"           % "2.3.3",
      "joda-time"              % "joda-time"            % "2.10.6",
      "org.slf4j"              % "slf4j-simple"         % "1.7.28" % "test",
      "org.scalatest"          %% "scalatest"           % "3.0.8" % "test",
      "io.circe"               %% "circe-parser"        % "0.13.0" % "test",
      "com.github.tomakehurst" % "wiremock"             % "2.26.3" % "test"
    )
  )
  .enablePlugins(AutomateHeaderPlugin)

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)

addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")
