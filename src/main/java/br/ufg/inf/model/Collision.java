package br.ufg.inf.model;

import lombok.Data;

import java.util.List;

@Data
public class Collision {
    private Integer idCollisionType;
    private CollisionLevel collisionLevel;
    private List<Institution> collisionSize;
}
