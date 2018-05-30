(ns {{name}}.shared.components
  (:require [rum.core :as rum]))

(rum/defc index []
  [:div.{{name}} "this text has a classname the same as the project! isn't that nifty?"
    [:div.bob "This is made green with css! Isn't that awesome! CSS is easy! I'm a designer now! Who needs Webflow when you have ClojureScript!

    nvm. grids are really hard. why are grids so difficult?! pay frontend designers, css is hard. I just wanted to center a div"]])

(rum/defc about []
  [:div.about "About Page"])
