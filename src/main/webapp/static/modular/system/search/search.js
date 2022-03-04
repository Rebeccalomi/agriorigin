/**
 * 物流管理管理初始化
 */
var 提取 = {
    id: "Table",	//表格id
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
提取.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '鸡蛋批次号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '物流中心名称', field: '物流中心名称', visible: true, align: 'center', valign: 'middle'},
        {title: '所在地址', field: '仓库地址', visible: true, align: 'center', valign: 'middle'},
        {title: '检测结果', field: '检测结果', visible: true, align: 'center', valign: 'middle'},
        {title: '时间', field: '时间', visible: true, align: 'center', valign: 'middle'},
        {title: '是否流入市场', field: '是否流入市场', visible: true, align: 'center', valign: 'middle'}
    ];
};


$(function () {
    var defaultColunms = 提取.initColumn();
    var table = new BSTable(提取.id, "/getegg/list", defaultColunms);
    table.setPaginationType("client");
    提取.table = table.init();
});