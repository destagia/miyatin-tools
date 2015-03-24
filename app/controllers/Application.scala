package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

import miyatin.tools._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def dijkstra = Action {
    Ok(views.html.dijkstra())
  }

  def convolution = Action {
    Ok(views.html.convolution())
  }

  def convolutionCalc(xn:String, yn:String) = Action {
    val x = xn.split(',').map(_.toFloat).toList
    val y = yn.split(',').map(_.toFloat).toList
    val z = Tools.foldCalc(x,y)
    println(x)
    println(y)
    println(z)
    Ok(Json.toJson(z))
  }

}