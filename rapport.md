#COMPTE-RENDU DE PROJET

Compte-rendu d'un projet de conception d'un moteur de jeu pour les échecs dans le cadre du cours de Programmation Orientée Objet (POO1).

A l'attention de : MM. DONINI, Pier & DECORVET, Grégoire

Auteurs : HOULMANN, Gildas & FRANCHETTI, Thibaud

Compte-rendu daté du 16 janvier 2020

## Choix d'implémentation

Un schéma UML complet est disponible en annexe.

Nous avons choisis de séparer les pièces de leurs règles afin de pouvoir factoriser et d'offrir une flexibilité sur la création de pièces.
Les pièces sont donc définies par un ensemble de règles de déplacement.

De plus, nous fournissons une classe classe mouvement (*Move*) définissant le mouvement d'une pièce 
d'une case à une autre et permmettant de s'appliquer lui-même sur un plateau (*Board*) ou d'inverser
ce mouvement sur ce même plateau.

Le déroulement classique d'une action est le suivant :

* L'utilisateur choisit une case de départ et une case de destination.
* Si une pièce se trouve sur le plateau à la case de départ, il est demandé à la pièce un mouvement
allant de la case de départ à la case de destination.
* La pièce vérifie pour l'ensemble de ses règles si un mouvement valide est possible, sinon, elle renvoie un mouvement invalide.
* Si le mouvement retourné est valide, le mouvement est appliqué sur le plateau.
* Si le mouvement n'a pas créé une situation d'échec pour le joueur courant, le mouvement est appliqué à une vue (*ChessView*),
sinon il est inversé.

## Avantages de la solution
* Il est très facile d'adapter le comportement de pièces existantes juste avec leur constructeur 
(par exemple pour jouer avec les blancs à gauche et les noirs à droite au lieu d'en-bas et d'en-haut)
* Il est facile de modifier/d'ajouter des pièces avec leur propre ensemble de règles.
* Il est relativmenent simple d'ajouter de nouvelles règles (qui peuvent avoir besoin ou non d'un nouveau type de mouvement).
* Le plateau peut être de toute forme et de toute taille.
* Un historique des mouvements est disponible pour de futures fonctionnalités.

## Défauts de la solution
* Il est nécessaire d'appliquer un mouvement d'abord sur une représentation interne du plateau avant de l'appliquer à nouveau sur 
l'affichage (un mouvement n'étant jamais 100% légal tant qu'il n'a pas été vérifié qu'il ne crée pas d'échec à son
propre camp).
* De nombreuses méthodes peuvent créer des situations douteuses si elles sont appliquées dans un contexte impropre,
mais n'étant pas destinées à être fournies à l'utilisateur final, il a été décidé de les laisser en l'état.


## Tests

### Avant-propos

Tous les mouvements décrits dans les tests ne mettent pas en échec le roi de la couleur du joueur actuel, sauf exceptions mentionnées.

De même, les tests concernent systématiquement les deux couleurs.

### Légende

✔️ : Mouvement possible

❌ : Mouvement impossible


### Tests généraux

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Le joueur blanc commence la partie|oui|oui|
|Le tour de jeu alterne entre les blancs et les noirs|oui|oui|
|Le bouton nouvelle partie fonctionne|oui|oui|
|Le message "Check!" s'affiche lorsque le roi est en échec|oui|oui|
|Le message "Check!" disparaît en cas de mouvement invalide|non|non|
|Le message "Check!" s'affiche lors d'une promotion si la nouvelle pièce met le roi en échec|oui|oui|
|Il est possible de découvrir son roi en bougeant une pièce|non|non|
|Il est possible de bouger une pièce qui couvre son roi lorsque celui-ci est en échec|oui|oui|
|Il est possible prendre une pièce qui met son roi en échec, si cela résout la menace et n'en crée pas de nouvelle|oui|oui|
|Il est possible de bouger une pièce lorsque son roi est en échec sans pour autant résoudre la menace|non|non|
|Il est possible de ne pas promouvoir une pièce|non|non|


### Pion

Note : Pour cette pièce, le terme "mouvement" désigne un déplacement sur une case adjacente, sauf exceptions mentionnées.

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Mouvement en avant sur une case libre|✔️|✔️|
|Mouvement en avant sur une case occupée|❌|❌|
|Mouvement en arrrière (diagonales comprises)/à gauche/à droite sur une case vide ou occupée|❌|❌|
|Mouvement de deux cases vers l'avant lors du premier déplacement si les deux cases frontales sont vides|✔️|✔️|
|Mouvement de deux cases vers l'avant lors du premier déplacement si au moins une des deux cases frontales est occupée|❌|❌|
|Mouvement de deux cases vers l'avant lors d'un déplacement ultérieur au premier si les deux cases frontales sont vides|❌|❌|
|Mouvement de plusieurs cases autre que les cas précédents dans n'importe quelle direction|❌|❌|
|Mouvement en diagonal-avant vers une case vide sans prise en passant|❌|❌|
|Mouvement en diagonal-avant vers une case occupée par un allié|❌|❌|
|Mouvement en diagonal-avant vers une case occupée par un adversaire|✔️|✔️|
|Prise en passant sur un pion ayant effectué un double mouvement au tour précédent|✔️|✔️|
|Prise en passsant sur un pion ayant effectué un mouvement simple au tour précédent|✔️|✔️|
|Prise en passant sur un pion n'ayant pas encore effectué de déplacement|❌|❌|
|Prise en passant sur un pion ayant effectué un double déplacement il y a deux tours au moins|❌|❌|
|Prise en passant sur n'importe quelle autre pièce qu'un pion ayant effectuée au tour précédent un déplacement de deux cases dans la direction inverse de celle d'avancée du pion|❌|❌|
|La promotion est possible par un mouvement vers l'avant¹|✔️|✔️|
|La promotion est possible par une prise diagonale¹|✔️|✔️|

¹La promotion n'est techniquement possible que pour ces deux déplacements, la prise en passant et le double mouvement n'étant pas supposé amener la pièce dans une zone de promotion, ils ne l'implémentent tout simplement pas.


### Roi

Note : Pour cette pièce, le terme "mouvement" désigne un déplacement sur une case adjacente dans n'importe quelle direction, sauf exceptions mentionnées.

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Mouvement vers une case libre|✔️|✔️|
|Mouvement vers une case occupée par un allié|❌|❌|
|Mouvement vers une case occupée par un adversaire|✔️|✔️|
|Mouvement de plusieurs cases (qui n'est pas un roque) dans n'importe quelle direction|❌|❌|
|Roque dans les règles (petit et grand) ¹|✔️|✔️|
|Roque avec une ou plusieurs pièces entre le roi et la tour²|❌|❌|
|Roque avec le roi en échec²|❌|❌|
|Roque avec une case attaquée sur le chemin²|❌|❌|
|Roque à un déplacement du roi autre que le premier²|❌|❌|
|Roque à un déplacement de la tour autre que le premier²|❌|❌|
|Roque avec la tour attaquée|✔️|✔️|
|Grand roque avec la case adjacente à la tour en échec|✔️|✔️|

¹Premier mouvement du roi et de la tour associée, case de destination correcte, le roi n'est pas en échec, aucune case entre le roi et la tour n'est occupée, aucune case sur le chemin du roi (destination incluse) n'est attaquée.

²Toutes les règles du roque sont supposées respectées sauf celle explicitement mentionnée.


### Reine

Note : Pour cette pièce, le terme "mouvement" désigne un déplacement rectiligne d'une ou plusieurs cases dans n'importe quelle direction, sauf exceptions mentionnées.

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Mouvement vers une case libre sans pièce sur le chemin|✔️|✔️|
|Mouvement vers une case libre ou occupée avec une ou plusieurs pièces sur le chemin|❌|❌|
|Mouvement vers une case occupée par un allié sans pièce sur le chemin|❌|❌|
|Mouvement vers une case occupée par un adversaire sans pièce sur le chemin|✔️|✔️|
|Mouvement non rectiligne|❌|❌|


### Fou

Note : Pour cette pièce, le terme "mouvement" désigne un déplacement rectiligne d'une ou plusieurs cases en diagonal, sauf exceptions mentionnées.

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Mouvement vers une case libre sans pièce sur le chemin|✔️|✔️|
|Mouvement vers une case libre ou occupée avec une ou plusieurs pièces sur le chemin|❌|❌|
|Mouvement vers une case occupée par un allié sans pièce sur le chemin|❌|❌|
|Mouvement vers une case occupée par un adversaire sans pièce sur le chemin|✔️|✔️|
|Mouvement non rectiligne|❌|❌|
|Mouvement suivant les axes|❌|❌|


### Tour

Note : Pour cette pièce, le terme "mouvement" désigne un déplacement rectiligne suivant les axes d'une ou plusieurs cases, sauf exceptions mentionnées.

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Mouvement vers une case libre sans pièce sur le chemin|✔️|✔️|
|Mouvement vers une case libre ou occupée avec une ou plusieurs pièces sur le chemin|❌|❌|
|Mouvement vers une case occupée par un allié sans pièce sur le chemin|❌|❌|
|Mouvement vers une case occupée par un adversaire sans pièce sur le chemin|✔️|✔️|
|Mouvement non rectiligne|❌|❌|
|Mouvement en diagonal|❌|❌|

### Cavalier

Note : Pour cette pièce, le terme "mouvement" désigne un "saut en L", sauf exceptions mentionnées.

|Description|Résultat attendu|Résultat obtenu|
|:-|:-:|:-:|
|Mouvement vers une case libre avec ou sans sans pièce sur le chemin|✔️|✔️|
|Mouvement vers une case occupée par un allié avec ou sans pièce sur le chemin|❌|❌|
|Mouvement vers une case occupée par un adversaire avec ou sans pièce sur le chemin|✔️|✔️|
|Mouvement non conventionnel|❌|❌|


