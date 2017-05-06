/**
 *
 * Created by jinyao on 2017/4/20.
 */
var vm = new Vue({
    el: '#mainApp',
    data: {
        mainItems: [],
        datailCareer:'',
    },
    filters: {},
    mounted: function () {
        this.$nextTick(function () {
            this.mainData();
        })
    },
    methods: {
        mainData: function () {
            var _this = this;
            this.$http.get('/career/findList').then(function (res) {
                _this.mainItems = res.data;
            });
        },
        detailCareer: function (id) {
            var _this = this;
            _this.$http.get('/career/findById/'+id).then(function (res) {
                this.datailCareer = res.data;
            })
        }
    }
});