/**
 * 生产商管理管理初始化
 */
var 生产商 = {
    id: "生产商Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
生产商.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '生产商编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '生产商名称', field: '生产商名称', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: '联系人', visible: true, align: 'center', valign: 'middle'},
            {title: '生产商地址', field: '生产商地址', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: '联系电话', visible: true, align: 'center', valign: 'middle'},
            {title: '邮编', field: '邮编', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
生产商.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        生产商.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加生产商管理
 */
生产商.openAdd生产商 = function () {
    var index = layer.open({
        type: 2,
        title: '添加生产商管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/生产商/生产商_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看生产商管理详情
 */
生产商.open生产商Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '生产商管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/生产商/生产商_update/' + 生产商.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除生产商管理
 */
生产商.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/生产商/delete", function (data) {
            Feng.success("删除成功!");
            生产商.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("生产商Id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询生产商管理列表
 */
生产商.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    生产商.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = 生产商.initColumn();
    var table = new BSTable(生产商.id, "/生产商/list", defaultColunms);
    table.setPaginationType("client");
    生产商.table = table.init();
});
