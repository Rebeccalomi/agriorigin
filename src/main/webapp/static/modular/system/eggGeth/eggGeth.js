/**
 * 区块链管理管理初始化
 */
var EggGeth = {
    id: "EggGethTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
EggGeth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '交易Hash', field: 'geth', visible: true, align: 'center', valign: 'middle'},
            {title: '交易信息', field: 'info', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
EggGeth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        EggGeth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加区块链管理
 */
EggGeth.openAddEggGeth = function () {
    var index = layer.open({
        type: 2,
        title: '添加区块链管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/eggGeth/eggGeth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看区块链管理详情
 */
EggGeth.openEggGethDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '区块链管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/eggGeth/eggGeth_update/' + EggGeth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除区块链管理
 */
EggGeth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/eggGeth/delete", function (data) {
            Feng.success("删除成功!");
            EggGeth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("eggGethId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询区块链管理列表
 */
EggGeth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    EggGeth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = EggGeth.initColumn();
    var table = new BSTable(EggGeth.id, "/eggGeth/list", defaultColunms);
    table.setPaginationType("client");
    EggGeth.table = table.init();
});
