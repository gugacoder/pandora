dia2dot
=======

(draft)

Dia-to-Dot é uma ferramenta para produção de diagramas de classe a partir de uma especificação em arquivo texto.
Dia-to-Dot converte o arquivo texto em um arquivo .dot do Graphviz e utiliza as ferramentas 'dot' e 'circo' para produzir o diagrama final em formato PNG, SVG ou qualquer outro formato suportado pelo Graphviz.

Especificação do Diagrama
-------------------------

Este é um exemplo da especificação do diagrama de classes:

    <<estereotipo>>
    Classe1<T>
    ---
    - Campo1: Tipo<T>
    + Campo2: Tipo<T>
    # Campo3: Tipo<T>
    ~ Campo4: Tipo<T>
    ---
    + Metodo(Arg1: Tipo<T>, Arg2: Tipo<T>): Tipo<T>
    + Rotina()
    
    <<estereotipo>>
    Classe2
    ---
    - Campo1: Tipo = "Abc"
    + Campo2: Tipo = 150
    # Campo3: Tipo
    ~ Campo4: Tipo
    ---
    + Metodo(Arg1: Tipo, Arg2: Tipo): Tipo
    + Rotina()
    
    Classe1 --> Classe2
    
    Um -- Associa
    Um --o Agrega
    Um --< Compoe
    Um --> Herda
    
    Um ~~ Usa
    Um ~~> Implementa 

Modo de Uso
-----------

    dia2dot FILE.dia [ --circo ]

Jan/2014  
Guga Coder
