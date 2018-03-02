package masters.vlad.humeniuk.notesviper.di.modules;

import dagger.Module;
import dagger.Provides;
import masters.vlad.humeniuk.notesviper.di.scopes.UserScope;
import masters.vlad.humeniuk.notesviper.presentation.base.BaseActivity;

@Module
public class ActivityModule {

    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @UserScope
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }
}
