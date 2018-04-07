
1.房间游戏结构===================================================================================

GameEngine(结构,房间管理,匹配管理)
|
GameZoneInfo implements TayeeZone (游戏大厅，包含多个房间类型)
|
-> Map<Integer,GamePlayer> hallPlayer;（大厅游客玩家列表）
-> Map<GameRoom> rooms;(大厅游戏房间列表)
-> key =(随机房间,金币房间,玩家房间)
|
GameGroupInfo implements TayeeGroup(游戏房间,包含不同类型的场次)
|
-> Map<GameMatch> matchs;(游戏场次)
-> key =(200分场次,500分场次,1000分场次)
|
GameMatchInfo  implements TayeeMatch,GameMatchAction,GamePlayerAction(比赛场次)
|
-> Map<Integer,GamePlayer> players；(参赛玩家)
-> GameMatchData data;(比赛数据)
-> GameMatchAction(比赛动作接口，比如 系统准备，发牌，通知，踢人...)
-> GamePlayerAction(玩家动作接口，比如 托管，跟注，加注...)

2.通信协议===================================================================================

MK_PLAYER(玩家) = 10000;
|
-> BUILD_PLAYER(创建角色)  = 10001;
-> ENTER_GAME(进入游戏) = 10002;
-> SAVE_DATA(保存数据) = 10003;
-> LOAD_RANK(读取排名) = 10004;
-> HEART_BEAT(连接心跳) = 19998	
-> EXIT_GAME(退出游戏) = 19999；
|
MK_MATCH(比赛)=20000；
|
—> RANDOM_MATCH(玩家自由匹配) = 20001；
—> ENTER_MATCH(玩家指定匹配) = 20002；
-> BUILD_MATCH(玩家创建匹配) = 20003；
-> ACTION_MATCH(游戏动作) = 20004;
-> ACTION_PLAYER(玩家动作) = 20005；
|













