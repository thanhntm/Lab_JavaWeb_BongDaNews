function getCategoryMenu() {
    $.ajax({
        url: "GetMenuBarCategory",
        type: 'GET',
        async: false,
        success: function (data) {
            var menuItem = generateCategoryElement(data.barList);
            var holder = $("#navbar");
            var elementBefore = holder.html();
            holder.html(menuItem);
            holder.append(elementBefore);
        }
    })
}

function generateCategoryElement(data) {
    if ($.isEmptyObject(data)) {
        return null;
    }
    var list = $("<ul class='nav navbar-nav'/>");
    list.append("<li class='hidden'><a href='#page-top'></a></li>");
    var item;
    for (i in data) {
        if (!$.isEmptyObject(data[i])) {
            item = $("<li class='dropdown'/>");
            item.append("<a class='page-scroll' href='Category?p=" + data[i].id + "'>" + data[i].categoryName + "</a>");
            if (!$.isEmptyObject(data[i].children)) {
                var childItem = $("<ul class='dropdown-menu collapse' aria-expanded='true'/>");
                for (y in data[i].children) {
                    childItem.append("<li><a href='Category?p=" + data[i].children[y].id + "'>" + data[i].children[y].categoryName + "</a></li>");
                }
                item.append(childItem);
            }
            list.append(item);
        }
    }
    return list;
}