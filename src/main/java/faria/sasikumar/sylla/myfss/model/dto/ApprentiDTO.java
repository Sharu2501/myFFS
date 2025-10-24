package faria.sasikumar.sylla.myfss.model.dto;

public class ApprentiDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String programme;
    private String majeure;
    private int annee;
    private boolean archived;

    public ApprentiDTO() {
    }

    public ApprentiDTO(Long id, String nom, String prenom, String email, String telephone, String programme, String majeure, int annee, boolean archived) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.programme = programme;
        this.majeure = majeure;
        this.annee = annee;
        this.archived = archived;
    }

    // --- Builder ---
    public static class Builder {
        private Long id;
        private String nom;
        private String prenom;
        private String email;
        private String telephone;
        private String programme;
        private String majeure;
        private int annee;
        private boolean archived;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder programme(String programme) {
            this.programme = programme;
            return this;
        }

        public Builder majeure(String majeure) {
            this.majeure = majeure;
            return this;
        }

        public Builder annee(int annee) {
            this.annee = annee;
            return this;
        }

        public Builder archived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public ApprentiDTO build() {
            return new ApprentiDTO(id, nom, prenom, email, telephone, programme, majeure, annee, archived);
        }
    }

    // --- Getters et Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getMajeure() {
        return majeure;
    }

    public void setMajeure(String majeure) {
        this.majeure = majeure;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    // --- Méthode statique pour accéder facilement au builder ---
    public static Builder builder() {
        return new Builder();
    }
}
