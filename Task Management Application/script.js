document.addEventListener('DOMContentLoaded', () => {
    const createTaskBtn = document.getElementById('createTaskBtn');
    const taskCreationModal = new bootstrap.Modal(document.getElementById('taskCreationModal'));
    const taskForm = document.getElementById('taskForm');
    const taskInput = document.getElementById('taskInput');
    const todoTasks = document.getElementById('todoTasks');

    createTaskBtn.addEventListener('click', () => {
        taskCreationModal.show();
    });

    taskForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const taskName = taskInput.value.trim();
        if (taskName) {
            const taskElement = document.createElement('div');
            taskElement.className = 'taskItem';
            taskElement.draggable = true;
            taskElement.textContent = taskName;
            todoTasks.appendChild(taskElement);
            taskInput.value = '';
            taskCreationModal.hide();
            addDragAndDropHandlers(taskElement);
        }
    });

    function addDragAndDropHandlers(taskItem) {
        taskItem.addEventListener('dragstart', handleDragStart);
        taskItem.addEventListener('dragend', handleDragEnd);
    }

    function handleDragStart(event) {
        event.dataTransfer.setData('text/plain', event.target.textContent);
        event.target.classList.add('dragging');
    }

    function handleDragEnd(event) {
        event.target.classList.remove('dragging');
    }

    document.querySelectorAll('.taskColumn').forEach(taskColumn => {
        taskColumn.addEventListener('dragover', (event) => {
            event.preventDefault();
        });

        taskColumn.addEventListener('drop', (event) => {
            event.preventDefault();
            const taskName = event.dataTransfer.getData('text/plain');
            const draggableTask = Array.from(document.querySelectorAll('.taskItem'))
                                    .find(task => task.textContent === taskName);
            if (draggableTask) {
                taskColumn.appendChild(draggableTask);
            }
        });
    });

    document.querySelectorAll('.taskItem').forEach(taskItem => {
        addDragAndDropHandlers(taskItem);
    });
});

