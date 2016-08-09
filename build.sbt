lazy val root = (project in file(".")).
  settings(
    name := "hmrc-exercise",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation"),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
