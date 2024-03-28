package org.jetbrains.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private int x;
    private int y;
}