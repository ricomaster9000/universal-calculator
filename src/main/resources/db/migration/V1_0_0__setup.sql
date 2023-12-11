/* ConstantEntities Class has sort of taken the place of using Flyway for database initial setup, it is because for initial data I wanted a more straightforward almsot cleaner way of using it as enums or in tests.
   There is another issues, some of those constant entities are indeed constant, as types/enums that will likely exist there always, I want to see the value of the enum/constant at the same place I define it, this
   makes it easier to use and easier to understand. In my eyes the "setup", if its indeed to add hardcoded data to a app, then it must be treated as hardcoded data, embedded strongly in the code itself, one place, instead
   of having to now define it here in the setup sql and also in the code. I am not 100% sure if this won't create issues later on, but for me it has made the code clearer and more testable.
   Its something new I tried, its not standard, but its something new, so far it has not dissapointed me, think of the ConstantEntities as a programattic way of doing the setup.sql
*/
