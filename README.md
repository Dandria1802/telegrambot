# This is my first pet project - telegrambot. Here is the description :
1. When a bot starts, user sees the instruction how to use an app - each ingredient must be a separate message.
2. Each massage from user mirroed by bot.
3. Each ingredient is located to ArrayList "dataFromUser".
4. When "dataFromUser" contains 5 ingredients, bot creates a multimap "keyValue", in multimap
strored all the ingredients to compare with user's ingredients. Each ingredient have 
an unique id, which is a "key".
5. Bot inverts multimap "keyValue" to a multimap "invertedKeyValue" (it is done to get an ingredient by a key)
6. Bot compares "dataFromUser" and "invertedKeyValue", get a "key" those ingredients that is the same in both
lists and put a "key" to another list - "dataToDb"
- I have a screenshot how my database looks like - recipes.jpg (pictures is also in the repository).
7. Bot compares each line (recipe) in database with "dataToDb", if line contains all ingredients from "dataToDb",
bot returns url of recipe.
Sorry for my english:)
