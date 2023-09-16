package example

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.{Headers, MediaType}
import org.http4s.Method._
import org.http4s.client.JavaNetClientBuilder
import org.http4s.implicits._
import org.http4s.client.dsl.io._
import org.http4s.headers.Accept

object Hello extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    JavaNetClientBuilder[IO].resource.use { client =>
      client.expect[String](GET(
        uri = uri"http://worldtimeapi.org/api/timezone",
        headers = Headers(Accept(MediaType.text.plain))))
    }.flatMap(IO.println).as(ExitCode.Success)
}
