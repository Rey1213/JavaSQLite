# Java Swing
![Swing](https://upload.wikimedia.org/wikipedia/commons/c/cc/Gui-widgets.png)
Swing es un conjunto de herramientas de widget GUI para Java. Forma parte de Java Foundation Classes (JFC) de Oracle: una API para proporcionar una interfaz gráfica de usuario (GUI) para programas Java.

Swing fue desarrollado para proporcionar un conjunto más sofisticado de componentes GUI. Swing proporciona una apariencia que emula la apariencia de varias plataformas, y también es compatible con una apariencia que se puede conectar y que permite que las aplicaciones tengan una apariencia no relacionada con la plataforma subyacente.

Swing no se implementa mediante un código específico de la plataforma. En su lugar, están escritos completamente en Java y, por lo tanto, son independientes de la plataforma. El término "peso ligero" se utiliza para describir un elemento de este tipo.

## JFrame

Es un contenedor de nivel superior que se utiliza para representar los requisitos mínimos para una ventana. 

Esto incluye los bordes, la capacidad de cambio de tamaño (¿se puede cambiar el tamaño de JFrame?), La barra de título, los controles (¿minimizar / maximizar permitido?), Y los controladores de eventos para varios eventos como windowClose, windowOpened etc.


## JPanel

Es una clase genérica utilizada para agrupar otros componentes.

Es útil cuando se trabaja con LayoutManagers, por ejemplo GridLayout, que agrega componentes a diferentes JPanels que luego se agregarán al JFrame para crear la GUI. Será más manejable en términos de diseño y reutilización.
También es útil para pintar / dibujar en Swing, deberías anular paintComponent (..) y, por supuesto, tener todas las ventajas del doble búfer.


## Eventos de Java Swing
![Swing Events](https://docs.oracle.com/javase/tutorial/figures/uiswing/events/2eventsource.gif)

Todas las aplicaciones GUI son impulsadas por Eventos. Una aplicación reacciona a diferentes tipos de eventos que se generan durante su vida útil. Los eventos son generados principalmente por el usuario de una aplicación, pero también pueden generarse por otros medios, como una conexión a Internet, un administrador de ventanas o un temporizador.

En el modelo de evento, hay 3 participantes:

* Event Source
* Event Object
* Event Listener

El Event Source (origen del evento) es el objeto cuyo estado cambia y genera eventos. 

El Event Object (objeto del evento) encapsula los cambios de estado en el origen del evento. 

El Event Listener (detector de eventos) es el objeto que quiere ser notificado. El objeto de origen del evento delega la tarea de manejar un evento en el detector de eventos.