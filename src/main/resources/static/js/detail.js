/**
 *
 * Created by jinyao on 2017/4/20.
 */
var vm = new Vue({
    el: '#mainApp',
    data: {
        datailCareer:'',
    },
    filters: {},
    mounted: function () {
        this.$nextTick(function () {
            this.mainData();
        })
    },
    methods: {
        detailCareer: function (id) {
            var _this = this;
            _this.$http.get('/career/findById/'+id).then(function (res) {
                this.datailCareer = res.data;
            })
        }
    }
});