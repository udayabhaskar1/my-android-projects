using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AndroidJSONDemo.Models;
using System.Diagnostics;

namespace AndroidJSONDemo.Controllers
{
    public class HomeController : Controller
    {
        public JsonResult GetPerson()
        {
            Person person = new Person()
            {
                name = "John",
                age = 32
            };

            return Json(person, JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public JsonResult SetPerson(Person person)
        {
            if (person != null)
            {
                Debug.WriteLine("name=" + person.name + ", age=" + person.age);
                return Json(new { status = "ok" });
            }

            return Json(new { status = "fail" });
        }
    }
}