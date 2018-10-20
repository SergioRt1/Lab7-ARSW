var SeriesRestController = (function () {
    var url = '';

    function getSeriesAlphavantage(name, type) {
        return axios.get(url+"/series/alphavantage/"+name+"/"+type).then(function (response) {
            return response.data;
        })
    }

    function getSeriesIntervalAlphavantage(name, type, interval) {
        return axios.get(url+"/series/alphavantage/"+name+"/"+type+"/"+interval).then(function (response) {
            return response.data;
        })
    }

    function getSeriesIextrading(name, type) {
        return axios.get(url+"/series/iextrading/"+name+"/"+type).then(function (response) {
            return response.data;
        })
    }

    function getSeriesDateIextrading(name, type, date) {
        return axios.get(url+"/series/iextrading/"+name+"/"+type+"/"+date).then(function (response) {
            return response.data;
        })
    }



    return {
        getSeriesAlphavantage: getSeriesAlphavantage,
        getSeriesIntervalAlphavantage: getSeriesIntervalAlphavantage,
        getSeriesIextrading: getSeriesIextrading,
        getSeriesDateIextrading: getSeriesDateIextrading

    };
})();