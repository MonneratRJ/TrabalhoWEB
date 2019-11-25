/* Global Variables */
var lastBtnID = "";
var lastAreaID = "";
var lastAlunoID = "";

/* Funções */
// Função de Navegação entre seções
function togglePage(id, btn) {
    lastBtnID = btn;
    lastAreaID = id;

    // Limpa todo os "active"
    $(".nav-item").each(function () {
        this.classList.remove("active");
    });

    // Esconde todas as seções
    $("#pagInicial").hide();
    $("#listaAluno").hide();
    $("#novoAluno").hide();
    $("#contato").hide();

    // Exibe a seção correta e atribui "active" ao link clicado
    $(id).show();
    $(btn).addClass("active");
}

// Função exclusão de aluno
function remover(id) {
    if (window.confirm("Deseja remover o aluno?")) {
        // Inicia requisição AJAX
        $.holdReady(true);
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                var response = JSON.parse(this.responseText);

                if (response["status"] == 0) {
                    // Notifica Usuário da inserção!
                    alert(response["msg"]);
                    $.holdReady(false);
                    refreshList();
                } else {
                    // Ocorreu algum erro!
                    alert(response["msg"]);
                    $.holdReady(false);
                }
            }
        };
        xmlhttp.open("POST", "js/jsonexclusao.json", true);
        xmlhttp.send(id);
    }
}

// Fução edição de aluno
function editar(id) {
    $("#editaAluno").trigger("reset");

    // Inicia requisição AJAX
    $.holdReady(true);
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var dados = JSON.parse(this.responseText);

            if (dados["id"] != -1) {
                // Não ocorreu nenhum erro, carregou um ID diferente de -1
                $("#editaAluno input[name=id]").val(dados["id"]);
                $("#editaAluno input[name=nome]").val(dados["nome"]);
                $("#editaAluno input[name=email]").val(dados["email"]);
                $("#editaAluno input[name=dataNasc]").val(dados["dataNasc"]);
                $("#editaAluno input[name=cpf]").val(dados["cpf"]);
                $("#editaAluno select[name=sexo]").val(dados["sexo"]);
                $("#editaAluno input[name=nomeSocial]").val(dados["nomeSocial"]);
                $("#editaAluno").show();
                $.holdReady(false);

                $("#editaAluno button[type=reset]").click(function () {
                    $("#editaAluno").trigger("reset");
                    $("#editaAluno").hide();
                })

                $("#editaAluno").submit(function () {
                    // Executa validação do formulário
                    if (validaForm("#editaAluno")) {
                        var formData = new FormData(document.getElementById("editaAluno"));
                        var json = JSON.stringify(Object.fromEntries(formData));

                        // Inicia requisição AJAX
                        $.holdReady(true);
                        var xmlhttp = new XMLHttpRequest();
                        xmlhttp.onreadystatechange = function () {
                            if (this.readyState === 4 && this.status === 200) {
                                var response = JSON.parse(this.responseText);

                                if (response["status"] == 0) {
                                    // Notifica Usuário da inserção!
                                    alert(response["msg"]);
                                    $("#editaAluno").trigger("reset");
                                    $("#editaAluno").hide();
                                    $.holdReady(false);
                                    refreshList();
                                } else {
                                    // Ocorreu algum erro!
                                    alert(response["msg"]);
                                    $.holdReady(false);
                                }

                            }
                        };
                        xmlhttp.open("POST", "js/jsonedicao.json", true);
                        xmlhttp.send(json);

                        return false;
                    } else {
                        return false;
                    }
                });
            } else {
                // Não encontrou um aluno, retornou ID = -1
                $.holdReady(false);
                alert("Não foi possível carregar as informações deste aluno!");
            }
        }
    };
    xmlhttp.open("POST", "js/jsonconsulta.json", true);
    xmlhttp.send(id);
}

// Função povoa lista através de JSON
function refreshList() {
    // Seleciona o tbody
    var tbody = document.querySelector('#listaAluno tbody');

    // Inicia requisição AJAX
    $.holdReady(true);
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var dataObject = JSON.parse(this.responseText);

            // Limpa tbody....
            tbody.innerHTML = "";

            // Verifica se tem resultados...
            if (dataObject.length > 0) {
                // Lista possui entradas...
                for (var i = 0; i < dataObject.length; ++i) {
                    // Inicia nova Linha (TR)
                    var tr = document.createElement('tr');

                    // Nome
                    var td = document.createElement('td');
                    td.appendChild(document.createTextNode(dataObject[i]["nome"]));
                    tr.appendChild(td);

                    // E-mail
                    var td = document.createElement('td');
                    td.appendChild(document.createTextNode(dataObject[i]["email"]));
                    tr.appendChild(td);

                    // Data Nascimento
                    var td = document.createElement('td');
                    td.appendChild(document.createTextNode(dataObject[i]["dataNasc"]));
                    tr.appendChild(td);

                    // CPF
                    var td = document.createElement('td');
                    td.appendChild(document.createTextNode(dataObject[i]["CPF"]));
                    tr.appendChild(td);

                    // Ícones e funções
                    var td = document.createElement('td');
                    td.innerHTML = "<span class='oi oi-wrench' title='Alterar Dados' onclick='editar(" + dataObject[i]["id"] + ")'></span> <span class='oi oi-x' title='Excluir' onclick='remover(" + dataObject[i]["id"] + ")'></span>";
                    tr.appendChild(td);

                    tbody.appendChild(tr);
                }
            } else {
                // Lista vazia!
                var tr = document.createElement('tr');
                var td = document.createElement('td');
                td.colSpan = 5;
                td.appendChild(document.createTextNode("Ainda não existem alunos inscritos!"));
                tr.appendChild(td);
                tbody.appendChild(tr);
            }

            $.holdReady(false);
        }
    };
    xmlhttp.open("GET", "js/jsonlista.json", true);
    xmlhttp.send();
}

// Função validação email
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

// Função validação idade
function getIdade(dataNasc) {
    dataArray = dataNasc.split("/");
    dob = new Date(dataArray[2], dataArray[1], dataArray[0]);
    var diff_ms = Date.now() - dob.getTime();
    var age_dt = new Date(diff_ms);
    return Math.abs(age_dt.getUTCFullYear() - 1970);
}

// Função validação CPF
function validateCPF(cpf) {
    var re = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
    return re.test(String(cpf));
}

function validaForm(id) {
    // Validação NOME >= 8 caracteres
    if ($(id + " input[name=nome]").val().length < 8) {
        alert("Preencha seu nome completo!");
        $(id + " input[name=nome]").focus();
        return false;
    }

    // Validação EMAIL
    var email = $(id + " input[name=email]").val();
    if (!validateEmail(email)) {
        alert("Favor verifique o email digitado!");
        $(id + " input[name=email]").focus();
        return false;
    }

    // Validação Data Nascimento
    var dataNasc = $(id + " input[name=dataNasc]").val();
    if (getIdade(dataNasc) < 16) {
        alert("Só aceitamos inscrições de maiores de 16 anos!");
        $(id + " input[name=dataNasc]").focus();
        return false;
    }

    // Validação CPF
    var cpf = $(id + " input[name=cpf]").val();
    if (!validateCPF(cpf)) {
        alert("Favor verifique o CPF informado!");
        $(id + " input[name=cpf]").focus();
        return false;
    }

    // Validação Sexo
    var sexo = $(id + " select[name=sexo]").val();
    if (sexo == null) {
        alert("Favor informar o campo sexo.");
        $(id + " select[name=sexo]").focus();
        return false;
    }
    return true;
}

/* Document.ready */
$(document).ready(function () {
    // Carrega a lista de Alunos
    refreshList();

    // Carrega dados sobre seções passadas que ficaram abertas
    if (localStorage.getItem('lastAreaID') !== null) {
        btn = localStorage.getItem('lastBtnID');
        id = localStorage.getItem('lastAreaID');
        togglePage(id, btn);
    } else {
        $("#pagInicial").show();
    }

    // Define a função dos links do menu superior
    $("#btnHome").click(function () {
        togglePage("#pagInicial", "#btnHome");
    });

    $("#btnLista").click(function () {
        refreshList();
        togglePage("#listaAluno", "#btnLista");
    });

    $("#btnCadastro").click(function () {
        togglePage("#novoAluno", "#btnCadastro");
    });

    $("#btnContato").click(function () {
        togglePage("#contato", "#btnContato");
    });

    // Cria máscaras para o formulário
    $("input[name=dataNasc]").mask("99/99/9999");
    $("input[name=cpf]").mask("999.999.999-99");

    // Cria listener para submit de form
    $("#formAluno").submit(function () {
        // Executa validação do formulário
        if (validaForm("#formAluno")) {
            // Captura as informações do formulário num formData e transforma em JSON
            var formData = new FormData(document.getElementById("formAluno"));
            var json = JSON.stringify(Object.fromEntries(formData));

            // Inicia requisição AJAX
            $.holdReady(true);
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    var response = JSON.parse(this.responseText);

                    if (response["status"] == 0) {
                        // Notifica Usuário da inserção!
                        alert(response["msg"]);
                        $("#formAluno").trigger("reset");
                        $.holdReady(false);
                    } else {
                        // Ocorreu algum erro!
                        alert(response["msg"]);
                        $.holdReady(false);
                    }

                }
            };
            xmlhttp.open("POST", "js/jsoncadastro.json", true);
            xmlhttp.send(json);

            return false;
        } else {
            return false;
        }
    });

    // Cria listener para fechamento do site salvar o status do usuário
    window.addEventListener('unload', function () {
        localStorage.setItem('lastAreaID', lastAreaID);
        localStorage.setItem('lastBtnID', lastBtnID);
    });

});