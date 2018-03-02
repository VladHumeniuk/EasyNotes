package masters.vlad.humeniuk.notesviper.domain.interactors;

import io.reactivex.Observable;

public interface InitDbInteractor {

    Observable<Long> init();
}
