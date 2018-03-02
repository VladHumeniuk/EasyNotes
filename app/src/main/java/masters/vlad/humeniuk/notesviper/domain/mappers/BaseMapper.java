package masters.vlad.humeniuk.notesviper.domain.mappers;

public interface BaseMapper<S, O> {

    O map(S instance);

    S mapBack(O instance);
}
