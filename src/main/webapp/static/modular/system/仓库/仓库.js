/**
 * 仓库管理管理初始化
 */
var 仓库 = {
    id: "仓库Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
仓库.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '仓库编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '所属物流中心编号', field: '所属物流中心编号', visible: true, align: 'center', valign: 'middle'},
            {title: '仓库地址', field: '仓库地址', visible: true, align: 'center', valign: 'middle'},
            {title: '仓库性质', field: '仓库性质', visible: true, align: 'center', valign: 'middle'},
            {title: '仓库总量', field: '仓库总量', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: '电话', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
仓库.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        仓库.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加仓库管理
 */
仓库.openAdd仓库 = function () {
    var index = layer.open({
        type: 2,
        title: '添加仓库管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/仓库/仓库_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看仓库管理详情
 */
仓库.open仓库Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '仓库管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/仓库/仓库_update/' + 仓库.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除仓库管理
 */
仓库.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/仓库/delete", function (data) {
            Feng.success("删除成功!");
            仓库.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("仓库Id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询仓库管理列表
 */
仓库.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    仓库.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = 仓库.initColumn();
    var table = new BSTable(仓库.id, "/仓库/list", defaultColunms);
    table.setPaginationType("client");
    仓库.table = table.init();
});
