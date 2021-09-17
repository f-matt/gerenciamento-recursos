# Projeto: Gerenciamento de Recursos

**Introdução**

O Sistema de Gerenciamento de Recursos oferecerá uma solução informatizada para o gerenciamento eficiente de recursos em geral. Deve ser suficientemente flexível para ser utilizado na gestão de diferentes recursos como espaço físico, recursos audiovisuais e de informática, veículos etc. Ao mesmo tempo, deve oferecer a robustez necessária para operar em alta disponibilidade e garantir a segurança das informações processadas. Finalmente, deve ser de fácil utilização, apresentando uma interface direta e amigável ao usuário.

**Histórias de Usuário**

Os administradores do sistema devem ser capazes de cadastrar recursos e agrupá-los em categorias para facilitar sua localização. Devem ser capazes, também, de manter os cadastros de operadores e solicitantes, contendo os dados pessoais dos usuários do sistema. Finalmente, os administradores devem ter acesso a relatórios gerenciais, que indiquem o uso de cada recurso, o volume de solicitações não atendidas etc. Tais informações serão utilizadas, por exemplo, para planejar a aquisição de novos recursos.

Os operadores serão responsáveis pela gestão das solicitações, registrando as movimentações de cada solicitação. Para tal, devem ser capazes de visualizar as solicitações em aberto, registrar empréstimos e devoluções.

Os solicitantes devem ser capazes de pesquisar os recursos, visualizando a disponibilidade de cada recurso. Poderão então, solicitar determinado recurso, informando o horário de uso desejado. Cada recurso terá um horário e um período máximo de uso definido, que devem ser verificados no ato da solicitação. Quando a solicitação é atendida, é registrada a mudança de status da mesma. No ato da devolução do recurso, a solicitação é finalizada. As solicitações não atendidas podem ser canceladas pelo solicitante ou por um operador.

O acesso de todos usuários ao sistema será realizado utilizando login e senha como credenciais de acesso. A cada usuário será vinculado um perfil, administrador, operador ou solicitante.

