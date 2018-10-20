var SeriesController = (function () {

    function clearTables() {
        var content = document.getElementById("content");
        var tables = document.getElementsByClassName("table");
        while (tables.length > 0) {
            content.removeChild(tables[0]);
        }
        var titles = document.getElementsByClassName("table-title");
        while (titles.length > 0) {
            content.removeChild(titles[0]);
        }
    }


    function addSeries(series) {
        var tableOrder = document.createElement("table");
        var header = document.createElement("tr");

        var cell = document.createElement("th");
        cell.innerHTML = "Date";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Open";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "High";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Low";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Close";
        header.appendChild(cell);

        var cell = document.createElement("th");
        cell.innerHTML = "Volume";
        header.appendChild(cell);


        tableOrder.appendChild(header);

        tableOrder.setAttribute("class", "table");
        for (i in series) {
            var row = document.createElement("tr");

            var cell = document.createElement("td");
            cell.innerHTML = series[i].date;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = series[i].open;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = series[i].high;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = series[i].low;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = series[i].close;
            row.appendChild(cell);

            var cell = document.createElement("td");
            cell.innerHTML = series[i].volume;
            row.appendChild(cell);

            tableOrder.appendChild(row);
        }
        var title = document.createElement("h4");
        title.setAttribute("class", "table-title");
        title.innerHTML = "Table";
        var firstTable = document.getElementById("HTMLtable");
        content.insertBefore(tableOrder, firstTable);
        content.insertBefore(title, tableOrder);
    }

    function getSelected(id) {
        var selectedTable = document.getElementById(id);
        var tableLabel = selectedTable.options[selectedTable.selectedIndex].value;
        return tableLabel;
    }


    function loadSeries(SerieBuilder, filterSource) {
        clearTables();
        loadData(SerieBuilder, filterSource);
    }

    function SerieAlphavantageBuilder(series) {
        var seriesBuild = [];
        var cont = 0;
        var TimeSeries;
        for(v in series){
            if(cont++ === 1){
                TimeSeries = v;
            }
        }
        for (i in series[TimeSeries]) {
            var serie = {date: i, open: series[TimeSeries][i]["1. open"], high: series[TimeSeries][i]["2. high"], low: series[TimeSeries][i]["3. low"], close: series[TimeSeries][i]["4. close"], volume: series[TimeSeries][i]["5. volume"]};
            seriesBuild.push(serie);
        }
        addSeries(seriesBuild);
    }

    function loadData(callback, filterSource) {
        axios.all([filterSource()])
                .then(axios.spread(function (serie) {
                    callback(serie);
                }));
    }

    function filterSelectedAlphavantage() {
        var typeSelected = getSelected("selectFunction");
        var name = document.getElementById("company").value;
        if (typeSelected === "Intraday") {
            var interval = getSelected("interval");
            return SeriesRestController.getSeriesIntervalAlphavantage(name, typeSelected, interval);
        }
        return SeriesRestController.getSeriesAlphavantage(name, typeSelected);
    }

    function filterSelectedIextrading() {
        var typeSelected = getSelected("selectFunction");
        var name = document.getElementById("company").value;
        if (typeSelected === "Date") {
            var date = document.getElementById("date").value;
            return SeriesRestController.getSeriesDateIextrading(name, typeSelected, date);
        }
        return SeriesRestController.getSeriesIextrading(name, typeSelected);
    }

    return {
        loadSeries: loadSeries,
        filterSelectedIextrading: filterSelectedIextrading,
        filterSelectedAlphavantage: filterSelectedAlphavantage,
        SerieIextradingBuilder: addSeries,
        SerieAlphavantageBuilder: SerieAlphavantageBuilder
    };
})();