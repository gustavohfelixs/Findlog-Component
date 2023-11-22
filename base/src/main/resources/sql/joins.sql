# Consulta Nome Estab e usuario
SELECT estab.nome_estab, 
usr.nome
from usuario_estabelecimento as usertab
INNER JOIN estabelecimento as estab
ON usertab.id_estab = estab.id
INNER JOIN usuario as usr
ON usertab.id_usuario =