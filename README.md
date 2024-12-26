# Projet : Citronix

## Contexte du Projet
Le projet Citronix consiste à développer une application de gestion pour une ferme de citrons, permettant aux fermiers de suivre la production, la récolte, et la vente de leurs produits.
L'application doit faciliter la gestion des fermes, champs, arbres, récoltes, et ventes, tout en optimisant le suivi de la productivité des arbres en fonction de leur âge.

## Fonctionnalités Principales

### Gestion des Fermes
- Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
- Recherche multicritère (Criteria Builder).

### Gestion des Champs
- Associer des champs à une ferme avec une superficie définie.
- Assurer la cohérence des superficies : la somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.

### Gestion des Arbres
- Suivre les arbres avec leur date de plantation, âge, et champ d'appartenance.
- Calculer l'âge des arbres.
- Déterminer la productivité annuelle en fonction de l'âge de l'arbre :
  - Arbre jeune (< 3 ans) : 2,5 kg / saison.
  - Arbre mature (3-10 ans) : 12 kg / saison.
  - Arbre vieux (> 10 ans) : 20 kg / saison.

### Gestion des Récoltes
- Suivre les récoltes par saison (hiver, printemps, été, automne).
- Une seule récolte par saison (tous les 3 mois).
- Enregistrer la date de récolte et la quantité totale récoltée.

### Détail des Récoltes
- Suivre la quantité récoltée par arbre pour une récolte donnée.
- Associer chaque détail de récolte à un arbre spécifique.

### Gestion des Ventes
- Enregistrer les ventes avec la date, le prix unitaire, le client, et la récolte associée.
- Calcul du revenu : **Revenu = quantité * prixUnitaire**.

> **NB** : Gestion = CRUD avec respect des contraintes.

## Contraintes

### Superficie des Champs
- **Superficie minimale** : La superficie d'un champ doit être au minimum de 0.1 hectare (1 000 m²).
- **Superficie maximale** : Aucun champ ne peut dépasser 50% de la superficie totale de la ferme.

### Limites des Champs
- **Nombre maximal de champs** : Une ferme ne peut contenir plus de 10 champs.

### Espacement entre les Arbres
- Chaque champ doit contenir un nombre d'arbres tel que la densité maximale est de 100 arbres par hectare (10 arbres par 1 000 m²).

### Durée de Vie des Arbres
- Un arbre ne peut être productif au-delà de 20 ans ; après cet âge, il est considéré comme non productif.

### Période de Plantation
- Les arbres ne peuvent être plantés qu'entre les mois de mars et mai, période idéale pour le climat.

### Limite Saisonnière
- Chaque champ ne peut être associé qu'à une seule récolte par saison.

### Règle des Arbres Non Récoltés Deux Fois
- Un arbre ne peut pas être inclus dans plus d’une récolte pour une même saison.

## Exigences Techniques

- **Spring Boot** : Utilisé pour créer l'API REST.
- **Architecture en couches** : Controller, Service, Repository, Entity.
- **Validation des données** : Réalisée avec les annotations Spring.
- **Utilisation des interfaces et implémentation** : Pour assurer la modularité.
- **Gestion centralisée des exceptions** : Pour une gestion propre des erreurs.
- **Tests unitaires** : Avec JUnit et Mockito.
- **Lombok et Builder Pattern** : Pour simplifier la gestion des entités.
- **MapStruct** : Pour la conversion entre les entités, DTO, et View Models.

---

Ce projet vise à fournir un outil efficace pour la gestion des fermes de citrons, en assurant une interface intuitive et des calculs précis pour améliorer la productivité agricole.

