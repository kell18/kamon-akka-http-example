

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server._
import akka.http.scaladsl.testkit._
import kamon.Kamon
import kamon.Kamon._
import kamon.context.Key
import kamon.influxdb.InfluxDBReporter
import org.scalatest._
import org.slf4j.LoggerFactory
import scala.concurrent.Future
import scala.util.control.NonFatal
import spray.json.DefaultJsonProtocol

class KamonExample extends WordSpec with Directives with SprayJsonSupport with Matchers with ScalatestRouteTest  {
  import scala.concurrent.ExecutionContext.Implicits.global

  Kamon.addReporter(new InfluxDBReporter(Kamon.config()))

  private val log = LoggerFactory.getLogger(getClass.getName)

  val future: Future[Int] = Future(123)

  val eHandler = ExceptionHandler {
    case NonFatal(_) =>
      log.error("3")
      complete("")
  }

  def r: Route =
    get {
      path("abc") {
        withTxKContext {
          log.info("1")
          handleExceptions(eHandler) {
            val mapped = future.map(_ * 2)
            onComplete(mapped) {
              _ =>
                log.info("2")
                throw new Exception("Test exception")
                complete("")
            }
          }
        }
      }
    }

  "R test" should {
    "propagate context" in {
      Get("/abc") ~> r ~> check {
        true shouldEqual true
      }
    }
  }

  private def withTxKContext[T](f: => T): T = {
    Kamon.withContext(Kamon.currentContext().withKey(Key.broadcast[String](name = "A", emptyValue = ""), "abc"))(f)
  }
}