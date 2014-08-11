Sample to demonstrate [EventBus][1] which has inited by https://github.com/kevintanhongann/EventBusSample

This Fork
---------

Added a 3rd fragment to demonstrate more about normal events and sticky-events. 

0. Refactoring codes on the original sample.
1. Use post() to update ActionBar's title.
2. Use post() to switch different Fragments.
3. Use postSticky() to update StickyEvent.
4. Update .gitignore and git-rm some objects.
5. Added BaseFragment to demonstrate coexistence between register and registerSticky.
6. Added NoStickyFragment that can handle normal event but unavailable for stickies.

  [1]: https://github.com/greenrobot/EventBus