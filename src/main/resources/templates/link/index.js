/**
 * Created by jinyao on 2017/4/25.
 */
/**
 *
 * Created by jinyao on 2017/4/20.
 */
var vm = new Vue({
    el: '#App',
    data: {
        page: [],
        datailItem: '',
    },
    filters: {},
    ready: function () {
        this.mainData();
        // this.detailItems();
    }
    ,
    methods: {
        mainData: function () {
            var _this = this;
            this.$http.get('/career/findList').then(function (response) {
                _this.page = response.data;
            });
        },
        detailItems: function (id) {
            var _this = this;
            _this.$http.get('/career/findById/' + id).then(function (response) {
                this.datailItem = response.data;
            })
        }
    }
});

var Detail = Vue.extend({
    template: '#detail',
    data: function () {
        return {
            msg: 'Hello, vue router!'
        }
    }
})

var Index = Vue.extend({
    template: '#index'
})

var router = new VueRouter()


router.map({
    '/index': {
        component: Index
    },
    '/detail': {
        component: Detail
    }
})

router.redirect({
    '/': '/home'
})

var App = Vue.extend({})
router.start(App, '#App')
