// System IP
const BASE_IP = 'localhost';
const API_BASE_URL = 'http://' + BASE_IP + ':16102/api/v1';
const LOG_BASE_URL = 'http://' + BASE_IP + ':16104/download/logs';
const FILE_BASE_URL = 'http://' + BASE_IP + ':16104/download/files';

function nmap() {
    let option = $("input[name='option']:checked").val();
    let ip;
    let port;
    if (option == 'ALL') {
        ip = $('#aip').val();
    }
    if (option == 'SET') {
        ip = $('#pip').val();
        port = $('#port').val();
    }
    $('#scanbtn').css("display", "none");
    $('#loadingbtn').css("display", "block");
    $('#aip').attr("disabled", true);
    $('#pip').attr("disabled", true);
    $('#port').attr("disabled", true);
    $('#allbtn').attr("disabled", true);
    $('#portbtn').attr("disabled", true);

    $.ajax({
        url: API_BASE_URL + '/nmap/execute',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            option: option,
            ip: ip,
            port: port
        }),
        dataType: "json",
        success: function (response) {
            if (response.result === "FAIL") {
                $('#scanbtn').css("display", "block");
                $('#loadingbtn').css("display", "none");
                $('#aip').attr("disabled", false);
                $('#pip').attr("disabled", false);
                $('#port').attr("disabled", false);
                $('#allbtn').attr("disabled", false);
                $('#portbtn').attr("disabled", false);
                alert(response.message);
                return;
            }
            $('#loadingbtn').css("display", "none");
            $('#successbtn').css("display", "block");
            $('#rsipdiv').text("IP : " + response.data.ip);
            $('#rstimediv').text(response.data.time + "s");

            $('#rslogdiv')
                .attr('href', LOG_BASE_URL + "/" + response.data.logName)
                .text(response.data.logName)
                .attr('target', '_blank');

            var tableBody = $('#rstbl');
            tableBody.empty();

            if (response.data && response.data.result && Object.keys(response.data.result).length > 0) {
                $.each(response.data.result, function (index, value) {
                    var row = '<tr>' +
                        '<td>' + index + '</td>' +
                        '<td>' + value.port + '</td>' +
                        '<td>' + value.state + '</td>' +
                        '<td>' + value.service + '</td>' +
                        '</tr>';
                    tableBody.append(row);
                });
            } else {
                var noDataRow = '<tr><td colspan="4">데이터가 존재하지 않습니다.</td></tr>';
                tableBody.append(noDataRow);
            }

            $('#rsdiv').css("display", "flex");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#scanbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#aip').attr("disabled", false);
            $('#pip').attr("disabled", false);
            $('#port').attr("disabled", false);
            $('#allbtn').attr("disabled", false);
            $('#portbtn').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function httrack() {
    let type = $("input[name='type']:checked").val();
    let url = $('#url').val();
    let api;
    if (type == 'httrack') {
        api = API_BASE_URL + '/httrack/execute';
    }
    if (type == 'wget') {
        api = API_BASE_URL + '/httrack/wget';
    }

    $('#httrackbtn').css("display", "none");
    $('#loadingbtn').css("display", "block");
    $('#url').attr("disabled", true);
    $('#httrack').attr("disabled", true);
    $('#wget').attr("disabled", true);

    $.ajax({
        url: api,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            type: type,
            url: url,
        }),
        dataType: "json",
        success: function (response) {
            if (response.result === "FAIL") {
                $('#httrackbtn').css("display", "block");
                $('#loadingbtn').css("display", "none");
                $('#url').attr("disabled", false);
                $('#httrack').attr("disabled", false);
                $('#wget').attr("disabled", false);
                alert(response.message);
                return;
            }
            $('#rslogdiv')
                .attr('href', LOG_BASE_URL + "/" + response.data.logName)
                .text(response.data.logName)
                .attr('target', '_blank');
            if (type == 'httrack') {
                $('#loadbtn').css("display", "block");
                $('#loadbtn').data('regDts', response.data.regDts);
                $('#terminatebtn').css("display", "block");
                return;
            }
            $('#loadingbtn').css("display", "none");
            $('#downloadbtn')
                .attr('href', FILE_BASE_URL + "/" + response.data.fileName)
                .attr('target', '_blank');
            $('#downloadbtn').css("display", "block");

        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#httrackbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#url').attr("disabled", false);
            $('#httrack').attr("disabled", false);
            $('#wget').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function httrackload() {
    let url = $('#url').val();
    let regDts = $('#loadbtn').data('regDts');
    $.ajax({
        url: API_BASE_URL + '/httrack/completion',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            url: url,
            regDts: regDts,
        }),
        dataType: "json",
        success: function (response) {
            if (response.data.complete) {
                $('#terminatebtn').css("display", "none");
                $('#loadingbtn').css("display", "none");
                $('#loadbtn').css("display", "none");
                $('#downloadbtn')
                    .attr('href', FILE_BASE_URL + "/" + response.data.fileName)
                    .attr('target', '_blank');
                $('#downloadbtn').css("display", "block");
            } else {
                alert(response.data.message);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#httrackbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#url').attr("disabled", false);
            $('#httrack').attr("disabled", false);
            $('#wget').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function crunch() {
    let minWord = $('#minWord').val();
    let maxWord = $('#maxWord').val();
    let words = $('#words').val();

    $('#crunchbtn').css("display", "none");
    $('#loadingbtn').css("display", "block");
    $('#minWord').attr("disabled", true);
    $('#maxWord').attr("disabled", true);
    $('#words').attr("disabled", true);

    $.ajax({
        url: API_BASE_URL + '/crunch/execute',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            minWord: minWord,
            maxWord: maxWord,
            words: words,
        }),
        dataType: "json",
        success: function (response) {
            if (response.result === "FAIL") {
                $('#crunchbtn').css("display", "block");
                $('#loadingbtn').css("display", "none");
                $('#minWord').attr("disabled", false);
                $('#maxWord').attr("disabled", false);
                $('#words').attr("disabled", false);
                alert(response.message);
                return;
            }
            $('#loadbtn').css("display", "block");
            $('#loadbtn').data('regDts', response.data.regDts);
            $('#terminatebtn').css("display", "block");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#crunchbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#minWord').attr("disabled", false);
            $('#maxWord').attr("disabled", false);
            $('#words').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function crunchload() {
    let regDts = $('#loadbtn').data('regDts');
    $.ajax({
        url: API_BASE_URL + '/crunch/progress',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            regDts: regDts,
        }),
        dataType: "json",
        success: function (response) {
            if (response.data.percent === "100%") {
                $('#loadingbtn').css("display", "none");
                $('#loadbtn').css("display", "none");
                $('#downloadbtn')
                    .attr('href', FILE_BASE_URL + "/" + response.data.logName)
                    .attr('download', response.data.logName);
                $('#downloadbtn').css("display", "block");
                $('#terminatebtn').css("display", "none");
            } else {
                alert("진행 : " + response.data.percent);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#crunchbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#minWord').attr("disabled", false);
            $('#maxWord').attr("disabled", false);
            $('#words').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function john() {
    let form;
    let johnOption = $("input[name='option']:checked").val();
    let targetFile;
    let customList;

    if (johnOption == "DEFAULT") {
        targetFile = $("#targetFile")[0].files[0];

        form = new FormData();
        form.append("targetFile", targetFile);
        form.append("johnOption", johnOption);

    }
    if (johnOption == "CUSTOM") {
        targetFile = $("#customFile")[0].files[0];
        customList = $("#customList")[0].files[0];

        form = new FormData();
        form.append("targetFile", targetFile);
        form.append("johnOption", johnOption);
        form.append("customList", customList);
    }

    $('#johnbtn').css("display", "none");
    $('#loadingbtn').css("display", "block");
    $('#default').attr("disabled", true);
    $('#custom').attr("disabled", true);
    $('#targetFile').attr("disabled", true);
    $('#customFile').attr("disabled", true);
    $('#customList').attr("disabled", true);

    $.ajax({
        url: API_BASE_URL + '/john/execute',
        type: "POST",
        processData: false,
        contentType: false,
        data: form,
        success: function (response) {
            if (response.result === "FAIL") {
                $('#johnbtn').css("display", "block");
                $('#loadingbtn').css("display", "none");
                $('#default').attr("disabled", false);
                $('#custom').attr("disabled", false);
                $('#targetFile').attr("disabled", false);
                $('#customFile').attr("disabled", false);
                $('#customList').attr("disabled", false);
                alert(response.message);
                return;
            }

            $('#rshashdiv')
                .attr('href', LOG_BASE_URL + "/" + response.data.hashName)
                .text(response.data.hashName)
                .attr('target', '_blank');

            $('#loadbtn').css("display", "block");
            $('#loadbtn').data('regDts', response.data.regDts);
            $('#terminatebtn').css("display", "block");

        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#johnbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#default').attr("disabled", false);
            $('#custom').attr("disabled", false);
            $('#targetFile').attr("disabled", false);
            $('#customFile').attr("disabled", false);
            $('#customList').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function johnload() {
    let regDts = $('#loadbtn').data('regDts');
    $.ajax({
        url: API_BASE_URL + '/john/completion',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            regDts: regDts,
        }),
        dataType: "json",
        success: function (response) {
            if (response.data.complete) {

                $('#rslogdiv')
                    .attr('href', LOG_BASE_URL + "/" + response.data.logName)
                    .text(response.data.logName)
                    .attr('target', '_blank');

                $('#loadingbtn').css("display", "none");
                $('#loadbtn').css("display", "none");
                $('#successbtn').css("display", "block");
                $('#rspwtext').text(response.data.password)
                $('#rspwdiv').css("display", "block");
                $('#terminatebtn').css("display", "none");
            } else {
                alert(response.data.message);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#httrackbtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            $('#url').attr("disabled", false);
            $('#httrack').attr("disabled", false);
            $('#wget').attr("disabled", false);
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function terminate(processName) {
    $.ajax({
        url: API_BASE_URL + '/system/terminate',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            processName: processName,
        }),
        dataType: "json",
        success: function (response) {
            if (response.result === "FAIL") {
                alert(response.message);
                return;
            }

            if ($('#systemResult').length > 0) {
                $('#systemResult').text(processName + ' 프로세스가 종료되었습니다.');
            } else {
                alert(processName + ' 프로세스가 종료되었습니다.');
                location.reload();
            }

            if (processName === "john") {
                $.ajax({
                    url: API_BASE_URL + '/system/terminate',
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({
                        processName: "john",
                    }),
                    dataType: "json",
                    success: function (response) {
                    }, error: function (jqXHR, textStatus, errorThrown) {
                    }
                });
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function systemTerminate() {
    let processName = $('#terminatename').val();
    terminate(processName);
}

function systemPid() {
    let processName = $('#pidname').val();
    $.ajax({
        url: API_BASE_URL + '/system/pid',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            processName: processName,
        }),
        dataType: "json",
        success: function (response) {
            if (response.result === "FAIL") {
                alert(response.message);
                return;
            }
            $('#systemResult').text('(' + processName + ') PID : ' + response.data.pid);
        }, error: function (jqXHR, textStatus, errorThrown) {
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function system() {
    let cmd = $('#command').val();
    $('#systembtn').css("display", "none");
    $('#loadingbtn').css("display", "block");
    $.ajax({
        url: API_BASE_URL + '/system/execute',
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            cmd: cmd,
        }),
        dataType: "json",
        success: function (response) {
            $('#systembtn').css("display", "block");
            $('#loadingbtn').css("display", "none");

            $('#systemResult').text(response.data.result);
            $('#rslogdiv')
                .attr('href', LOG_BASE_URL + "/" + response.data.logName)
                .text(response.data.logName)
                .attr('target', '_blank');

        }, error: function (jqXHR, textStatus, errorThrown) {
            $('#systembtn').css("display", "block");
            $('#loadingbtn').css("display", "none");
            var response = JSON.parse(jqXHR.responseText);
            if (response && response.message) {
                alert(response.message);
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        }
    });
}

function copy() {
    var pageUrl = "https://github.com/hoowave/SecuriSuite";
    var $temp = $("<textarea>");
    $("body").append($temp);
    $temp.val(pageUrl).select();
    document.execCommand("copy");
    $temp.remove();
    alert("프로젝트 URL이 클립보드에 복사되었습니다.");
}