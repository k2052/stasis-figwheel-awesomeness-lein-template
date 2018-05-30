# stasis-figwheel-awesomeness

A Leiningen template for [stasis](https://github.com/magnars/stasis). It has universal rendering, hot-reloading (via Figwheel), css-in-cljs (via garden), and npm dependencies. It is minimal but has some batteries included so you can power all sorts of projects.

## Features

- figwheel and live reloading dev awesomeness via [boot-figreload](https://github.com/boot-clj/boot-figreload)
- Static exporting with [stasis](https://github.com/magnars/stasis)
- css via [boot-garden](https://github.com/martinklepsch/boot-garden)
- client side routing via [bidi](https://github.com/juxt/bidi)
- npm awesomeness. manage your JS dependencies like a communist in Oakland!
- universal/isomorphic/futuristic/blockchain rendering via static html generated to `dist/` and a hot reloading ring handler for dev awesomeness

## Usage

First generate your project:

```sh
$ lein new stasis-figwheel-awesomeness butts
```

Then install npm deps:

```sh
$ npm i .
```

And run it with:

```sh
$ boot dev
```

Figwheel will open your browser and you can start editing your pages.

*Note*: You'll need npm to because this uses awesome npm libraries like React.
*Note Note*: You can actually skip the npm step at the moment I just documented it here for the future. Thanks current me, you are awesome.

### Building and Deploying

You can build it by running

```sh
$ npm run build
```

You can deploy it by pushing `dist/` to your favorite static site host. I highly recommend the static site host everyone wants to work at [Netlify](https://www.netlify.com/).

## Roadmap/Future Procrastinations

- remove dependency on npm (when it isn't needed for js stuff or deployment)
- Support multiple frameworks (respo, reagent planned)
- Sqircle support (this is top secret)
- fix the bugs/gotchas (will save this for last and accidentally prioritize new features)

## Is this any good?

Yes! And it is entirely harmless! No bitcoin was mined to create this!

## Gotchas/Bugs

If you break your ring config figwheel won't care and won't bother loading it. A good way to catch this a logging statement that checks things are all working. For example:

```clj
(println "ring ready! prepare your butts for hyperspace jumps")
```

Sometimes on a cold boot the ring handler will throw an error page. This is because there are no files ready for it serve in `resources/` and it doesn't like this, waiting a second and then refreshing "fixes" it. Sometimes the future is a lot like PHP.

## FAQ

No questions have been asked yet. What were you expecting here?

## License

Copyright © 2018 K-2052

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
