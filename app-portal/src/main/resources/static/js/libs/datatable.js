/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers, if any.
 */

/**
 *
 * @author sog
 * @version 1.0
 */
'use strict';

var Datatables = function () {

    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;


    return {
        //main function to initiate the model
        init: function (options) {

            if (!$().dataTable) {
                return;
            }
            the = this;
            options = $.extend(true, {
                src: "", // actual table
                resetGroupActionInputOnSuccess: true,
                loadingMessage: message['datatable.loading"'],
                dataTable: {
                    dom: 'rt<"bottom"ip>',
                    pageLength: 10,
                    language: {
                        metronicGroupActions: '_TOTAL_ 行已经选择:  ',
                        metronicAjaxRequestGeneralError:message['datatable.NoInternet'],
                        processing: message['datatable.Processing'],
                        lengthMenu: '显示 _MENU_ 项结果',
                        zeroRecords: '没有匹配结果',
                        info: message['datatable.info'],
                        infoEmpty: message['datatable.infoEmpty'],
                        infoFiltered: '(由 _MAX_ 项结果过滤)',
                        infoPostFix: '',
                        search: '搜索:',
                        url: '',
                        emptyTable: message['datatable.dataIsEmpty'],
                        loadingRecords: '载入中...',
                        infoThousands: ',',
                        paginate: {
                            first: message['datatable.First'],
                            previous: message['datatable.Previous'],
                            next: message['datatable.Next'],
                            last: message['datatable.Last']
                        },
                        aria: {
                            sortAscending: ': 以升序排列此列',
                            sortDescending: ': 以降序排列此列'
                        }
                    },
                    searching: false,
                    orderCellsTop: true,
                    columnDefs: [{
                        orderable: false,
                        targets: [0]
                    }],

                    autoWidth: false,
                    processing: false,
                    serverSide: true,
                    scrollX:true,//水平滚动
                    responsive: false,//关闭响应式效果,否则以上设置无效

                    ajax: {
                        url: '',
                        type: 'POST',
                        timeout: 20000,
                        showLoadingTip: false,
                        dataType: 'json',
                        contentType: 'application/json',
                        data: function (data) {
                            if (ajaxParams) {
                                data['params'] = ajaxParams;
                            }
                            return JSON.stringify(data);
                        },
                        dataSrc: function (res) {
                            if (res.customActionStatus) {
                                if (tableOptions.resetGroupActionInputOnSuccess) {
                                    $('.table-group-action-input', tableWrapper).val("");
                                }
                            }

                            if ($('.group-checkable', table).size() === 1) {
                                $('.group-checkable', table).attr("checked", false);
                            }

                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the, res);
                            }
                            return res.data;
                        },
                        error: function () {
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }

                        }
                    },

                    "drawCallback": function (oSettings) {
                        if (tableInitialized === false) {
                            tableInitialized = true;
                            table.show();
                        }

                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                    }
                }
            }, options);

            tableOptions = options;

            table = $(options.src);
            tableContainer = table.parents(".table-container");

            var tmp = $.fn.dataTableExt.oStdClasses;

            $.fn.dataTableExt.oStdClasses.sWrapper += ' dataTables_extended_wrapper';
            $.fn.dataTableExt.oStdClasses.sFilterInput = 'form-control input-xs input-sm input-inline';
            $.fn.dataTableExt.oStdClasses.sLengthSelect = 'form-control input-xs input-sm input-inline';

            if(options.scrollY){
                var height = $('#sidebar_left').height(); // 获取当前界面的高度
                var tableTopStr = table.offset().top;
                options.dataTable.scrollY = height - (parseInt(tableTopStr) + 65);
            }

            dataTable = table.DataTable(options.dataTable);
            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');
            // 去掉边框
            tableWrapper.css('border', 0);

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper)
                    .html($('.table-actions-wrapper', tableContainer).html()); // place the panel
                                                                               // inside the
                                                                               // wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template
                                                                      // container
            }

            // handle filter submit button click
            table.on('click', '.filter-submit', function (e) {
                e.preventDefault();
                the.submitFilter();
            });

            // handle filter cancel button click
            table.on('click', '.filter-cancel', function (e) {
                e.preventDefault();
                the.resetFilter();
            });

            // 全选事件

            table.on('click', 'thead > tr > th.check_group input[type="checkbox"]:not(.check_item)', function(e){
                var checkboxs = $('label.check_item > input:checkbox[name="dt_checkbox"]', table);
                if($(this).prop("checked")){
                    checkboxs.prop("checked", true);
                }else{
                    checkboxs.prop("checked",false);
                }
                // checkboxs.click();
            })
        },

        submitFilter: function () {

            dataTable.ajax.reload();
            // 提交后清空数据
            // the.clearAjaxParams();
        },

        resetFilter: function () {
            $('textarea.form-filter, select.form-filter, input.form-filter', table)
                .each(function () {
                    $(this).val("");
                });
            $('input.form-filter[type="checkbox"]', table).each(function () {
                $(this).attr("checked", false);
            });
            the.clearAjaxParams();
            dataTable.ajax.reload();
        },

        getSelectedRowsCount: function () {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function () {
            var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table)
                .each(function () {
                    rows.push($(this).val());
                });
            return rows;
        },

        getCheckRowsData: function () {
            var rows = [];
            var  datas = dataTable.data();
            table.find('tr.on .input-hide-tr').each(function (index, item) {
                datas.each(function(data){
                    if(data.id === $(item).data('id')){
                        rows.push(data);
                    }
                });
            });
            return rows;
        },

        getSelectedRowsData: function () {
            var rows = [];
            var  datas = dataTable.data();
            table.find('tr label.check_item input:checkbox:checked').each(function (index, item) {
                datas.each(function(data){
                    if(data.id == $(item).val()){
                        rows.push(data.id);
                    }
                });
            });
            return rows;
        },
        /*获取所选行所有数据*/
        getSelectedRowsTableData: function () {
            var rows = [];
            var  datas = dataTable.data();
            table.find('tr label.check_item input:checkbox:checked').each(function (index, item) {
                datas.each(function(data){
                    if(data.id == $(item).val()){
                        rows.push(data);
                    }
                });
            });
            return rows;
        },

        setAjaxParam: function (name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function (name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = '';
            }
            // Fixd 去掉数组的方式传递
            ajaxParams[name] = value;
        },

        clearAjaxParams: function (name, value) {
            ajaxParams = {};
        },

        getDataTable: function () {
            return dataTable;
        },

        getTableWrapper: function () {
            return tableWrapper;
        },

        gettableContainer: function () {
            return tableContainer;
        },

        getTable: function () {
            return table;
        }

    };

};

var DTColumnKit = function () {
    var checkbox = '<form class="smart-form"><label class="checkbox option block mn" style="font-style: ">'+
        '<input type="checkbox" value="001"> '+
        ' <i></i></label></form>';

    /**
     * 时间戳格式化函数
     * @param  {string} format    格式
     * @param  {int}    timestamp 要格式化的时间 默认为当前时间
     * @return {string}           格式化的时间字符串
     */
    function date(format, timestamp) {
        var a, jsdate = ((timestamp) ? new Date(timestamp * 1000) : new Date());
        var pad = function (n, c) {
            if ((n = n + "").length < c) {
                return new Array(++c - n.length).join("0") + n;
            } else {
                return n;
            }
        };
        var txt_weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var txt_ordin = {1: "st", 2: "nd", 3: "rd", 21: "st", 22: "nd", 23: "rd", 31: "st"};
        var txt_months = ["", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        var f = {
            // Day
            d: function () {return pad(f.j(), 2)},
            /**
             * @return {string}
             */
            D: function () {return f.l().substr(0, 3)},
            j: function () {return jsdate.getDate()},
            l: function () {return txt_weekdays[f.w()]},
            N: function () {return f.w() + 1},
            /**
             * @return {string}
             */
            S: function () {return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th'},
            w: function () {return jsdate.getDay()},
            z: function () {return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0},

            // Week
            /**
             * @return {number}
             */
            W: function () {
                var a = f.z(), b = 364 + f.L() - a;
                var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;
                if (b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b) {
                    return 1;
                } else {
                    if (a <= 2 && nd >= 4 && a >= (6 - nd)) {
                        nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
                        return date("W", Math.round(nd2.getTime() / 1000));
                    } else {
                        return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
                    }
                }
            },

            // Month
            /**
             * @return {string}
             */
            F: function () {return txt_months[f.n()]},
            m: function () {return pad(f.n(), 2)},
            /**
             * @return {string}
             */
            M: function () {return f.F().substr(0, 3)},
            n: function () {return jsdate.getMonth() + 1},
            t: function () {
                var n;
                if ((n = jsdate.getMonth() + 1) === 2) {
                    return 28 + f.L();
                } else {
                    if (n & 1 && n < 8 || !(n && 1) && n > 7) {
                        return 31;
                    } else {
                        return 30;
                    }
                }
            },

            // Year
            /**
             * @return {number}
             */
            L: function () {
                var y = f.Y();
                return (!(y && 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0
            },
            //o not supported yet
            /**
             * @return {number}
             */
            Y: function () {return jsdate.getFullYear()},
            y: function () {return (jsdate.getFullYear() + "").slice(2)},

            // Time
            a: function () {return jsdate.getHours() > 11 ? "pm" : "am"},
            /**
             * @return {string}
             */
            A: function () {return f.a().toUpperCase()},
            /**
             * @return {number}
             */
            B: function () {
                // peter paul koch:
                var off = (jsdate.getTimezoneOffset() + 60) * 60;
                var theSeconds = (jsdate.getHours() * 3600) + (jsdate.getMinutes() * 60) + jsdate.getSeconds() + off;
                var beat = Math.floor(theSeconds / 86.4);
                if (beat > 1000) beat -= 1000;
                if (beat < 0) beat += 1000;
                if ((String(beat)).length === 1) beat = "00" + beat;
                if ((String(beat)).length === 2) beat = "0" + beat;
                return beat;
            },
            g: function () {return jsdate.getHours() % 12 || 12},
            /**
             * @return {number}
             */
            G: function () {return jsdate.getHours()},
            h: function () {return pad(f.g(), 2)},
            H: function () {return pad(jsdate.getHours(), 2)},
            i: function () {return pad(jsdate.getMinutes(), 2)},
            s: function () {return pad(jsdate.getSeconds(), 2)},
            //u not supported yet

            // Timezone
            //e not supported yet
            //I not supported yet
            O: function () {
                var t = pad(Math.abs(jsdate.getTimezoneOffset() / 60 * 100), 4);
                if (jsdate.getTimezoneOffset() > 0) t = "-" + t; else t = "+" + t;
                return t;
            },
            /**
             * @return {string}
             */
            P: function () {
                var O = f.O();
                return (O.substr(0, 3) + ":" + O.substr(3, 2))
            },
            //T not supported yet
            //Z not supported yet

            // Full Date/Time
            c: function () {return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":" + f.i() + ":" + f.s() + f.P()},
            //r not supported yet
            /**
             * @return {number}
             */
            U: function () {return Math.round(jsdate.getTime() / 1000)}
        };

        return format.replace(/[\\]?([a-zA-Z])/g, function (t, s) {
            var ret;
            if (t !== s) {
                // escaped
                ret = s;
            } else if (f[s]) {
                // a date function exists
                ret = f[s]();
            } else {
                // nothing special
                ret = s;
            }
            return ret;
        });
    }

    return {
        order: {
            width: '40px',
            orderable: false,
            render: function (row, type, set, meta) {
                return meta.settings._iDisplayStart + meta.row + 1;
            },
            className: 'text-center dt_index',
            title: message['datatable.number']
        },
        checkbox: {
            width: '20px',
            orderable: false,
            title: checkbox,
            targets: 0,
            className: 'text-center check_group',
            render: function (data, type, row) {
                // return '<label class="option block mn check_item">' +
                //     '<input type="checkbox" value="' + row.id + '" name="dt_checkbox"> ' +
                //     '<span class="checkbox mn">' +
                //     '</span>' +
                //     '</label>';

                return '<form class="smart-form"><label class="checkbox option block mn check_item" style="font-style: ">'+
                    '<input type="checkbox" value="' + row.id + '" name="dt_checkbox"> '+
                    ' <i></i></label></form>';
            }
        },
        /*为了不进行全选*/
        checkboxone: {
            width: '20px',
            orderable: false,
            // title: checkbox,
            targets: 0,
            className: 'text-center check_group',
            render: function (data, type, row) {
                // return '<label class="option block mn check_item">' +
                //     '<input type="checkbox" value="' + row.id + '" name="dt_checkbox"> ' +
                //     '<span class="checkbox mn">' +
                //     '</span>' +
                //     '</label>';

                return '<form class="smart-form"><label class="checkbox option block mn check_item" style="font-style: ">'+
                    '<input type="checkbox" value="' + row.id + '" name="dt_checkbox"> '+
                    ' <i></i></label></form>';
            }
        }
        , dateRender: function (data) {
            if (data === 0) {
                return '1970-01-01'
            }
            return date('Y-m-d', data);
        }
        , ymdHmRender: function (data) {
            if (data === 0) {
                return '1970-01-01 08:00'
            }
            return date('Y-m-d H:i', data);
        }
    }
}();
