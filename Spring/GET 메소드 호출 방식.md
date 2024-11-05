## Post 방식 -> Get 방식 으로 변경 

#### post 방식

1. VO에 Map 형식이 있어도 `@RequestBody` 가 인식해 map으로 데이터를 받아준다. 

```js
// api Call
const params = {
    tile_id: String(id),
    queryParam: {
        op: 'abc',
        version: '1',
        tileID: String(id),
    }
};
```

```java
// VO
public class TileMapSearchVO {
	private String tile_id;
	private String data_version;
	
	@ApiModelProperty(value = "Key-value pairs", dataType = "Map[String,Object]")
	private Map<String, Object> queryParam;
}
```

```java
// Controller
@PostMapping("/tileMapController/cmd=tile-map-info")
public ModelAndView getTileMapInfo(@RequestBody TileMapSearchVO tileVO) throws Exception {
    Map<String,Object> map = tileMapService.getTileMapInfo(tileVO);
}
```

#### Get 방식1

1. Parameter를 개별적으로 받아와  VO의 Map 에 세팅하는 방법 

```js
// api Call
const params = {
    tile_id: String(id),
    op: 'abc',
    version: '1',
    tileID: String(id),
};
```

```java
// Controller
@GetMapping("/tileMapController/cmd=tile-map-info")
public ModelAndView getTileMapInfo(
    @RequestParam String tile_id,
    @RequestParam String op,
    @RequestParam String version,
    @RequestParam String tileID, 
    HttpServletResponse response ) throws Exception {

    Map<String, Object> queryParam = new HashMap<String, Object>();
    TileMapSearchVO tileVO = new TileMapSearchVO();
    queryParam.put("op", op);
    queryParam.put("version", version);
    queryParam.put("tileID", tileID);
    tileVO.setTile_id(tile_id);
    tileVO.setQueryParam(queryParam);
    
    Map<String,Object> map = tileMapService.getTileMapInfo(tileVO);
}
```

#### Get 방식2

1. Parameter를 VO로 받아와 세팅하는 방법 
2. `@ModelAttribute` 이 Map 형식에 자동 Mapping이 되지 않아 호출시 파라미터의 형태를 map으로 보내줘야한다.
   1. 즉, API 호출시 params의 형태를 Map으로 변경해줘야 한다. 
3. 네트워크 Request형태 
   1. `http://127.0.0.1:8080/tileMapController/cmd=tile-map-info?&tile_id=1&queryParam.op=뮻&queryParam.version=1&queryParam.tileID=1`

```js
// api Call
const params = {
    tile_id: String(id),
    'queryParam.op' : 'gamv',
    'queryParam.version' : '1',
    'queryParam.tileID' : String(id),
};
```

```java
// Controller
@GetMapping("/tileMapController/cmd=tile-map-info")
public ModelAndView getTileMapInfo(@ModelAttribute TileMapSearchVO tileVO, HttpServletResponse response) throws Exception {
	Map<String,Object> map = tileMapService.getTileMapInfo(tileVO);
}

```

