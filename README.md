# Карточная игра "дуэль" #

В эту игру играют два игрока, у каждого из которых на руках 12 карточек с числами от 0 до 11. 
Первый игрок выбирает карту из имеющихся у него на руках и выкладывает на стол рубашкой вверх. Число на выбранной карте будет являться "атакой" игрока. После этого второй игрок выбирает карту из оставшихся у него на руках и также выкладывает её рубашкой вверх. Это его "защита". После этого игроки одновременно переворачивают карты, и защищающийся игрок получает столько штрафных очков насколько "атака" первого игрока превышает защиту "второго". В следующем раунде игроки меняются местами.
Игра заканчивается, когда у игроков не остаётся карт на руках. Выигрывает игрок получивший меньше всего штрафных очков. 
Необходимо реализовать консольный вариант игры в которой соперником будет ИИ. Постарайтесь придумать алгоритм при котором ИИ будет максимально эффективно действовать против игрока (при этом он не должен иметь информации какую карту выбирает игрок на текущем ходу). Приложите также текстововое описание вашей идеи/алгоритма.

#### Примечания: ####

1) Задача должна быть реализована на языке для выбранного вами курса. Java - для Java-интенсива и Javascript/Typescript для Vue.js-интенсива
2) Ваша первоочередная задача сделать рабочий вариант игры. От кода который не запускается пользы мало
3) Алгоритм может быть сколько угодно сложным, но не забывайте про п.2 — лучше более простой, но рабочий вариант.
4) Старайтесь написать код максимально "красиво", это не олимпиадная задача и читаемость тоже оценивается
5) Подумайте об удобстве для играющего. UX важен даже для простого приложения