function supprimerOffre(idOffre, url) {
    if (confirm('Etes - vous sure de supprimer cet offre.') === true) {
        window.location.href = url + idOffre;
    }
}

let overall = document.querySelectorAll('input[type="checkbox"]');
overall.forEach(query => {
    query.addEventListener('click', function (e) {
        e.preventDefault();
        let url = "/admin/user/setstatususer?user=" + e.target.name
        window.location.href = url;
    });
});