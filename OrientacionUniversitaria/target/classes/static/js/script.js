const API_URL = 'http://localhost:8080/api';

document.addEventListener('DOMContentLoaded', () => {
    checkUserSession();
    
    if (document.getElementById('contenedor-carreras')) {
        cargarCarreras();
    }
    if (document.getElementById('contenedor-facultades')) {
        cargarFacultades();
    }
});

function checkUserSession() {
    const user = JSON.parse(localStorage.getItem('usuario_edu_guia'));
    const userNav = document.getElementById('user-nav-section');
    if (user && userNav) {
        userNav.innerHTML = `
            <span class="text-white me-3 font-weight-bold"><i class="fa-solid fa-user-graduate me-1"></i>Hola, ${user.nombre}</span>
            <button onclick="logout()" class="btn btn-outline-light btn-sm rounded-pill"><i class="fa-solid fa-power-off me-1"></i>Salir</button>
        `;
    }
}

function logout() {
    localStorage.removeItem('usuario_edu_guia');
    window.location.reload();
}

function toggleForm(formType) {
    const formLogin = document.getElementById('form-login');
    const formRegister = document.getElementById('form-register');
    const formTitle = document.getElementById('form-title');
    const alertBox = document.getElementById('alert-box');
    alertBox.innerHTML = '';

    if (formType === 'register') {
        formLogin.style.display = 'none';
        formRegister.style.display = 'block';
        formTitle.innerHTML = '<i class="fa-solid fa-user-plus me-2"></i>Registro de Estudiante';
    } else {
        formRegister.style.display = 'none';
        formLogin.style.display = 'block';
        formTitle.innerHTML = '<i class="fa-solid fa-user-circle me-2"></i>Iniciar Sesión';
    }
}

async function handleLogin(e) {
    e.preventDefault();
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    try {
        const response = await fetch(`${API_URL}/usuarios/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const usuario = await response.json();
            localStorage.setItem('usuario_edu_guia', JSON.stringify(usuario));
            showAlert('¡Inicio de sesión exitoso! Redirigiendo...', 'success');
            setTimeout(() => { window.location.href = 'index.html'; }, 1200);
        } else {
            showAlert('Credenciales incorrectas. Verificá tu correo y contraseña.', 'danger');
        }
    } catch (error) {
        showAlert('Error al conectar con el servidor backend.', 'danger');
    }
}

async function handleRegister(e) {
    e.preventDefault();
    const nuevoUsuario = {
        nombre: document.getElementById('reg-nombre').value,
        apellido: document.getElementById('reg-apellido').value,
        dni: parseInt(document.getElementById('reg-dni').value),
        email: document.getElementById('reg-email').value,
        password: document.getElementById('reg-password').value,
        interesPrincipal: document.getElementById('reg-interes').value,
        tipo: 'ESTUDIANTE'
    };

    try {
        const response = await fetch(`${API_URL}/usuarios`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoUsuario)
        });

        if (response.ok) {
            showAlert('¡Cuenta creada exitosamente! Iniciá sesión a continuación.', 'success');
            setTimeout(() => { toggleForm('login'); }, 1500);
        } else {
            showAlert('Error al registrar usuario.', 'danger');
        }
    } catch (error) {
        showAlert('Error al conectar con el servidor.', 'danger');
    }
}

function showAlert(message, type) {
    const alertBox = document.getElementById('alert-box');
    if (alertBox) {
        alertBox.innerHTML = `<div class="alert alert-${type} py-2" role="alert">${message}</div>`;
    }
}

// Cargar Carreras en Inicio
async function cargarCarreras(area = '') {
    const contenedor = document.getElementById('contenedor-carreras');
    if (!contenedor) return;

    let url = `${API_URL}/carreras`;
    if (area) {
        url += `?area=${encodeURIComponent(area)}`;
    }

    try {
        const res = await fetch(url);
        const carreras = await res.json();
        
        if (carreras.length === 0) {
            contenedor.innerHTML = `<div class="col-12"><div class="alert alert-info text-center">No se encontraron carreras para la área seleccionada.</div></div>`;
            return;
        }

        contenedor.innerHTML = carreras.map(c => `
            <div class="col-md-6 col-lg-4">
                <div class="card h-100 border-0 shadow-sm rounded-4">
                    <div class="card-body">
                        <span class="badge bg-primary-subtle text-primary mb-2">${c.areaEstudio || 'General'}</span>
                        <h5 class="card-title fw-bold text-dark">${c.nombre}</h5>
                        <p class="card-text text-muted small">${c.descripcion || 'Sin descripción disponible.'}</p>
                    </div>
                    <div class="card-footer bg-transparent border-0 d-flex justify-content-between align-items-center pb-3">
                        <span class="small text-muted"><i class="fa-solid fa-clock me-1"></i>${c.duracionAnos} años</span>
                        <span class="badge bg-info text-dark">${c.modalidad}</span>
                    </div>
                </div>
            </div>
        `).join('');
    } catch (err) {
        contenedor.innerHTML = `<div class="col-12"><div class="alert alert-warning text-center">Para ver las carreras en tiempo real, asegurate de ejecutar el backend Spring Boot.</div></div>`;
    }
}

function buscarCarreras() {
    const select = document.getElementById('select-area');
    if (select) {
        cargarCarreras(select.value);
    }
}

// Cargar Facultades
async function cargarFacultades() {
    const contenedor = document.getElementById('contenedor-facultades');
    if (!contenedor) return;

    try {
        const res = await fetch(`${API_URL}/facultades`);
        const facultades = await res.json();

        contenedor.innerHTML = facultades.map(f => `
            <div class="col-md-6 col-lg-4">
                <div class="card h-100 border-0 shadow-sm rounded-4">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <span class="badge bg-secondary">${f.sigla}</span>
                        </div>
                        <h5 class="card-title fw-bold">${f.nombre}</h5>
                        <h6 class="card-subtitle mb-2 text-primary fw-semibold">${f.universidad}</h6>
                        <p class="card-text text-muted small"><i class="fa-solid fa-location-dot me-1"></i>${f.ubicacion}</p>
                    </div>
                    <div class="card-footer bg-transparent border-0 pb-3">
                        <button class="btn btn-outline-primary btn-sm w-100 rounded-pill" onclick="verDetalleFacultad(${f.id})">
                            <i class="fa-solid fa-circle-info me-1"></i>Ver Información
                        </button>
                    </div>
                </div>
            </div>
        `).join('');
    } catch (err) {
        contenedor.innerHTML = `<div class="col-12"><div class="alert alert-warning text-center">Asegurate de iniciar el Backend Spring Boot en el puerto 8080 para cargar las facultades.</div></div>`;
    }
}

async function verDetalleFacultad(id) {
    try {
        const res = await fetch(`${API_URL}/facultades/${id}`);
        const f = await res.json();

        document.getElementById('modal-facultad-titulo').innerText = `${f.nombre} (${f.sigla})`;
        document.getElementById('modal-facultad-universidad').innerText = f.universidad;
        document.getElementById('modal-facultad-ubicacion').innerHTML = `<i class="fa-solid fa-location-dot me-1"></i>${f.ubicacion}`;
        document.getElementById('modal-facultad-descripcion').innerText = f.descripcion;
        document.getElementById('modal-facultad-web').href = f.sitioWeb;

        const modal = new bootstrap.Modal(document.getElementById('modalFacultad'));
        modal.show();
    } catch (err) {
        alert('Error al cargar información de la facultad');
    }
}
