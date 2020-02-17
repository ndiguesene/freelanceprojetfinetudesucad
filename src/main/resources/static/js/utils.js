function supprimerOffre(idOffre, url) {
    if (confirm('Etes - vous sure de supprimer cet offre.') === true) {
        console.log("/recruteur/supprimerOffre?offre=" + idOffre);
        window.location.href = url + idOffre;
    } else {
        console.log("RIEN " + idOffre + ' URL = ' + url);
    }
}

// "/recruteur/supprimerOffre?offre="