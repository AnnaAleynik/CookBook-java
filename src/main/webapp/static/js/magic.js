document.addEventListener('DOMContentLoaded', () => {
    item = 3;

    function addIngredient() {
        div = document.getElementById("items");
        newitem = "<div class=\"input-group mb-2 mt-2\">";
        newitem += "<input class=\"form-control col-4 mr-3 form-control\" type=\"text\" name=\"item" + item + "\" placeholder=\"Ингредиент\">";
        newitem += "<input class=\"col-2 form-control\" type=\"text\" name=\"amount" + item + "\" placeholder=\"Количество\"></div>";
        newnode = document.createElement("div");
        newnode.innerHTML = newitem;
        div.insertBefore(newnode, button);
        amount = document.getElementById("amount");
        amount.setAttribute("value", item);
        item++;
    }

    button = document.getElementById("add");
    button.addEventListener("click", () => {
        addIngredient(button);

    });

    // var input, filter, ul, li, a, i;
    function filterFunction() {
        input = document.getElementById("tagInput");
        filter = input.value.toUpperCase();
        div = document.getElementById("tagChoice");
        a = div.getElementsByTagName("option");
        for (i = 0; i < a.length; i++) {
            txtValue = a[i].textContent || a[i].innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                a[i].style.display = "";
            } else {
                a[i].style.display = "none";
            }
        }
    }
});