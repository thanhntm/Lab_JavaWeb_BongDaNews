$(document).ready(function () {
    var menuBarHolder = $("#menuBarHolder");
    var hiddenHolder = $("#hiddenHolder");
    getCategoryMap();
    var updateOutput = function (e) {
        var list = e.length ? e : $(e.target),
                output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));
        } else {
            output.val('JSON browser support required for this demo.');
        }
    };

    // activate Nestable for menu bar list 
    menuBarHolder.nestable({
        group: 1
    });

    // activate Nestable for hidden list 
    hiddenHolder.nestable({
        group: 1
    });

    $('#nestable-menu').on('click', function (e) {
        var target = $(e.target),
                action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });
});
function getNewMap(mapHolder) {
    var holder = $("#" + mapHolder);
    holder = holder.nestable('serialize')
    return holder;
}
function saveMap() {
    var menuBarData = JSON.stringify(getNewMap("menuBarHolder"));
    var hiddenData = JSON.stringify(getNewMap("hiddenHolder"));
    $.ajax({
        url: "SaveNewCategoryOrder",
        type: 'POST',
        data: {
            barList: encodeURIComponent(menuBarData),
            hiddenList: encodeURIComponent(hiddenData)
        },
        success: function (data) {
            if (String(data) === "success") {
                alert("Update order successfully!");
            }
        }
    })
}

function getCategoryMap() {
    $.ajax({
        url: "GetMenuBarCategory",
        type: 'GET',
        async: false,
        success: function (data) {
            var menuList = generateCategoryElement(data.barList);
            var hiddenList = generateCategoryElement(data.hiddenList);
            $("#menuBarHolder").html("");
            $("#menuBarHolder").append(menuList);
            $("#hiddenHolder").html("");
            $("#hiddenHolder").append(hiddenList);
        }
    })
}


function generateCategoryElement(data) {
    if ($.isEmptyObject(data)) {
        return $("<div class='dd-empty'/>");
    }
    var list = $("<ol class='dd-list'/>");
    var item;
    for (i in data) {
        if (!$.isEmptyObject(data[i])) {
            item = $("<li class='dd-item' data-id='" + data[i].id + "'>");
            item.append("<div class='dd-handle'>" + data[i].categoryName + "</div>");
            if (!$.isEmptyObject(data[i].children)) {
                item.append(generateCategoryElement(data[i].children));
            }
            list.append(item);
        }
    }
    return list;
}