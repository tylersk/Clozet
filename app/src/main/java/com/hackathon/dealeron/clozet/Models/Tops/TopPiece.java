package com.hackathon.dealeron.clozet.Models.Tops;

import com.hackathon.dealeron.clozet.Models.ClothingPiece;
import com.hackathon.dealeron.clozet.Models.Color;
import com.hackathon.dealeron.clozet.Models.DressType;
import com.hackathon.dealeron.clozet.Models.WeatherType;

import java.util.List;

/**
 * Created by tskalabrin on 8/18/2017.
 */

public class TopPiece extends ClothingPiece {
    public TopType type;
    public TopPiece(Color color, List<DressType> dressTypes, List<WeatherType> weatherType, TopType type) {
        super(color, dressTypes, weatherType);
        this.type = type;
    }
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        TopPiece other =(TopPiece)obj;
        if (this.type != other.type) {
            return false;
        }
        return super.equals(other);
    }
    @Override
    public int hashCode() {
        return 0;
    }
}
